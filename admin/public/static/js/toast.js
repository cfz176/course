


toast = {
  success: function (msg) {
    Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: msg,
      showConfirmButton: false,
      timer: 900
    })
  },
  error: function (msg) {
    Swal.fire({
      position: 'top-end',
      icon: 'error',
      title: msg,
      showConfirmButton: false,
      timer: 900
    })
  },
  warning: function (msg) {
    Swal.fire({
      position: 'top-end',
      icon: 'warning',
      title: msg,
      showConfirmButton: false,
      timer: 900
    })
  }
}