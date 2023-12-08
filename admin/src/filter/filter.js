/**
 * 过滤数组
 * @param key eg:C
 * @param list eg: [{key:"C",value:"收费"},{key:"F",value:"免费"}]
 * @returns {string} eg: 收费
 */
function optionFilter(list, key) {
  if (!key || !list) {
    return ''
  } else {
    let result = '';
    for (let i = 0; i < list.length; i++) {
      if (key === list[i]['key']) {
        result = list[i]['value']
      }
    }
    return result;
  }
}

/**
 * 过滤对象
 * @param object
 * @param key
 * @returns {string}
 */
function optionObjectFilter(object, key) {
  if (!key || !object) {
    return ''
  } else {
    let result = '';
    for (let enums in object) {
      if (key === object[enums]['key']) {
        result = object[enums]['value']
      }
    }
    return result;
  }
}

/**
 * 格式化时间
 * @param value 0000000
 * @return {string} 00:00:00
 */
function formatSecond(value) {
  value = value || 0;
  let second = parseInt(value,10); //秒
  let minute = 0; //分
  let hour = 0; //时

  //秒大于60进行处理，小于不处理
  if (second > 60) {
    minute = Math.floor(second / 60);
    second = Math.floor(second % 60);
    //分大于60进行处理
    if (minute > 60) {
      hour = Math.floor(minute / 60);
      minute = Math.floor(minute % 60);
    }
  }
  //秒
  let result = prefixInteger(second, 2)+"";
  //分
  result = prefixInteger(minute, 2) + ":" + result;
  //时
  result = prefixInteger(hour, 2) + ":" + result;
  return result;
}

function prefixInteger(num, length) {
  return (Array(length).join('0')+num).slice(-length);
}

export {
  optionFilter,
  optionObjectFilter,
  formatSecond
}