var taskList = {};
(function () {
    taskList.init = function () {
        var dataUrl = "../../../sys/quartz/job/pageList?beanName=" + $("#name").val() + "&status=" + $("#status").val();
        var table = layui.table,
            form = layui.form;
        //执行渲染
        table.render({
            elem: '#taskTable', //指定原始表格元素选择器（推荐id选择器）
            height: $(window).height() - 200,
            page: true, //开启分页
            loading: true,
            id: 'taskTable',
            even: true,
            cols: [[ //标题栏
                /* {checkbox: true, fixed: true},*/
                {field: 'jobId', title: '编号', width: 70, sort: true},
                {field: 'options', title: '操作', width: 100, align: 'center', toolbar: '#optionsBtn'},
                {field: 'beanName', title: '类别', width: 200},
                {field: 'methodName', title: '方法名', width: 200},
                {field: 'params', title: '参数', width: 100},
                {field: 'cronExpression', title: '表达式', width: 200},
                {field: 'status', title: '状态', width: 70, templet: '#statusStyle'},
                {field: 'gmtCreateStr', title: '创建时间', width: 200, sort: true},
                {field: 'remark', title: '备注', width: 250},
                {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#taskBtn'}
            ]],
            method: "post",
            url: dataUrl,
            done: function (res, curr, count) {
                //tips提示
                $("i[lay-event='run']").hover(function () {
                    tipsi = layer.tips('立即执行任务', this, {time: 0, tips: [1, '#black']});
                }, function () {
                    layer.close(tipsi);
                });
                $("i[lay-event='pause']").hover(function () {
                    tipsi = layer.tips('暂停任务', this, {time: 0, tips: [1, '#black']});
                }, function () {
                    layer.close(tipsi);
                });
            }
        });
        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            //立即启动
            if (obj.event === 'run') {
                taskList.run(data);
            }
            //暂停任务
            if (obj.event === 'pause') {
                taskList.pause(data);
            }
            if (obj.event === 'updateTask') {
                taskList.addPage(data.jobId);
            }
            if (obj.event === 'deleteTask') {//删除
                layer.confirm('确定要删除吗？',
                    {
                        btn: ['确定', '取消'], //可以无限个按钮
                        icon: 0
                    },
                    function (index, layero) {
                        $.ajax({
                            url: "../../../sys/quartz/job/remove",
                            traditional: true,
                            data: {id: [data.jobId]},
                            success: function (data) {
                                if (data.result_data.flag == "SUCCESS") {
                                    layer.close(index);
                                    taskList.init();
                                    layer.msg(data.result_data.msg, {icon: 6, shift: 5});
                                } else {
                                    layer.msg(data.result_data.msg, {icon: 5, shift: 6});
                                }
                            }
                        });
                    });
            }
        });

    };
    taskList.addPage = function (jobId) {
        layer.open({
            type: 2,
            title: "定时任务调度平台",
            maxmin: true,
            area: ['400px'],
            fixed: true, //不固定
            content: "addTask.html?jobId=" + jobId,
            success: function (layero, index) {
                //调整弹出框自适应
                layer.style(index, {top: '20%'});
                layer.iframeAuto(index);
            },
            end: function () {
            }
        });
    };

    //立即启动
    taskList.run = function (data) {
        layer.confirm('确定要立即执行此任务吗？', {btn: ['确定', '取消'], icon: 0},
            function (index, layero) {
                $.ajax({
                    url: "../../../sys/quartz/job/run",
                    traditional: true,
                    data: {id: [data.jobId]},
                    success: function (data) {
                        if (data.result_data.flag == "SUCCESS") {
                            layer.msg(data.result_data.msg, {icon: 6, shift: 5});
                            taskList.init();
                        } else {
                            layer.msg(data.result_data.msg, {icon: 5, shift: 6});
                        }
                    }
                });
            });
    };

    //暂停任务
    taskList.pause = function (data) {
        layer.confirm('确定要暂停此任务吗？', {btn: ['确定', '取消'], icon: 0},
            function (index, layero) {
                $.ajax({
                    url: "../../../sys/quartz/job/pause",
                    traditional: true,
                    data: {id: [data.jobId]},
                    success: function (data) {
                        if (data.result_data.flag == "SUCCESS") {
                            layer.msg(data.result_data.msg, {icon: 6, shift: 5});
                            taskList.init();
                        } else {
                            layer.msg(data.result_data.msg, {icon: 5, shift: 6});
                        }
                    }
                });
            });
    };

//暂停任务
    taskList.enable = function (data) {
        layer.confirm('确定要暂停此任务吗？', {btn: ['确定', '取消'], icon: 0},
            function (index, layero) {
                $.ajax({
                    url: "../../../sys/quartz/job/enable",
                    traditional: true,
                    data: {id: [data.jobId]},
                    success: function (data) {
                        if (data.result_data.flag == "SUCCESS") {
                            layer.msg(data.result_data.msg, {icon: 6, shift: 5});
                            taskList.init();
                        } else {
                            layer.msg(data.result_data.msg, {icon: 5, shift: 6});
                        }
                    }
                });
            });
    }

}());


  
  