Loading = {
  show: function () {
    $.blockUI({
      message: '<img src="../../static/image/loading.gif" />',
      css: {
        zIndex: "1000",
        padding: "10px",
        left: "50%",
        width: "80px",
        marginLeft: "-40px",
      }
    });
  },
  hide: function () {
     setTimeout(function () {
      $.unblockUI();
    }, 300)
  }
};