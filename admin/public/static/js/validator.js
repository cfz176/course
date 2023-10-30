Validator = {

  /**
   * 非空校验
   * @param value
   * @param text
   * @returns {boolean}
   */
  require: function (value,text) {
    if (Tool.isEmpty(value)) {
      console.log(1)
      Toast.warning(text + "不能为空");
      return false;
    } else {
      return true;
    }
  },

  /**
   * 长度校验
   */
  length: function (value,text,min,max) {
    if (!Tool.isLength(value, min, max)) {
      Toast.warning(text + "长度为" +min+ "~"+ max + "位");
      return false;
    } else {
      return true;
    }
  }

};