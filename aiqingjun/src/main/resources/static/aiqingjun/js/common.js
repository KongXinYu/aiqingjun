var Constans = {
    SUCCESS_CODE : 0
};

/**
 * 对ajax扩展
 */
;(function($) {
    // 获取$.ajax对象
    var _ajax = $.ajax;

    $.ajax = function (options) {
        // 请求默认配置
        var fn = {
            success: function (res, textStatus) {},
        }

        options.success && (fn.error = options.success);

        var _options = $.extend(options, {
            url : ctx + options.url,
            success: function (res, textStatus) {
                fn.success(res, textStatus);
                // 成功后跳转
                if (options.to && res.status === Constans.SUCCESS_CODE) {
                    window.location.href = ctx + options.to;
                }
            }
        });

        // 将最新的参数传回ajax对象
        _ajax(_options);
    }
})(jQuery);