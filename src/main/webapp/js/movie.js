$(document).ready(function() {
    document.getElementById("queryForm").addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent form submission

        var inputValue = document.getElementById("query").value;
        var baseUrl = window.location.href.split('?')[0];
        var pageParam = 'page=' + page ;
        var newUrl = baseUrl + '?' + pageParam;
        var queryParam = 'query=' + inputValue;
        newUrl = newUrl + '&' + queryParam;
        window.location.href = newUrl;
    });

    var parameters = getUrlParameters(window);
    var page = parameters["page"];
    page = page == null ? 1 : parseInt(page);
    if(parameters["query"]!=null) {
        document.getElementById("query").value = parameters["query"];
    }
    document.getElementById("next").addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent form submission

        var baseUrl = window.location.href.split('?')[0];
        var queryParam = 'page=' + (page + 1);
        var newUrl = baseUrl + '?' + queryParam;
        if (parameters["query"] != null) {
            newUrl += "&query=" + parameters["query"];
        }
        window.location.href = newUrl;
    });

    if (document.getElementById("previous") != null) {
        document.getElementById("previous").addEventListener("submit", function (event) {
            event.preventDefault(); // Prevent form submission

            var baseUrl = window.location.href.split('?')[0];
            var queryParam = 'page=' + (page - 1);
            var newUrl = baseUrl + '?' + queryParam;
            if (parameters["query"] != null) {
                newUrl += "&query=" + parameters["query"];
            }

            window.location.href = newUrl;
        });
    }
});