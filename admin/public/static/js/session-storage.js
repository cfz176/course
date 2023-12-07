SessionStorage =  {
  get: function (key) {
    var v = sessionStorage.getItem(key);
    if (v && typeof (v) !== "undefined" && v !== "undefined") {
      return JSON.parse(v);
    }
  },

  set: function (key,data) {
    sessionStorage.setItem(key, JSON.stringify(data));
  },

  remove: function (key) {
    sessionStorage.remove(key);
  },

  clearAll: function () {
    sessionStorage.clearAll();
  }

};