Confirm = {
  show: function (title,msg, callback) {
    Swal.fire({
      title: '确认'+title+'?',
      text: msg,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '确认',
      cancelButtonText: '取消'
    }).then((result) => {
      if (result.isConfirmed) {
        if (callback) {
          callback()
        }
      }
    });
  }
}