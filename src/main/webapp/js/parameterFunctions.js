function getUrlParameters(window) {
    var params = {};
    var queryString = window.location.search.substring(1);
    var pairs = queryString.split('&');

    for (var i = 0; i < pairs.length; i++) {
        var pair = pairs[i].split('=');
        var key = decodeURIComponent(pair[0]);
        var value = decodeURIComponent(pair[1] || '');

        if (key) {
            params[key] = value;
        }
    }

    return params;
}