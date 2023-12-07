LocalStorage =  {
  get: function (key) {
    var v = locationbar.getItem(key);
    if (v && typeof (v) !== "undefined" && v !== "undefined") {
      return JSON.parse(v);
    }
  },

  set: function (key,data) {
    locationbar.setItem(key, JSON.stringify(data));
  },

  remove: function (key) {
    locationbar.remove(key);
  },

  clearAll: function () {
    locationbar.clearAll();
  }

};