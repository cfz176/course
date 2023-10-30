Tool = {
  /**
   * 为空校验
   */
  isEmpty: function (obj) {
    if (typeof obj == 'string') {
      return (!obj || obj.replace("/\s+/g", "") == "");
    } else {
      return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
    }
  },

  /**
   * 非空校验
   */
  isNotEmpty: function (obj) {
    return !this.isEmpty(obj);
  },

  /**
   * 长度校验
   */
  isLength: function (str,min,max) {
    let len = $.trim(str).length
    return len >= min && len <= max;
  }

}
