/**
 * 过滤数组
 * @param key eg:C
 * @param list eg: [{key:"C",value:"收费"},{key:"F",value:"免费"}]
 * @returns {string} eg: 收费
 */
function optionFilter(list,key){
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

function optionObjectFilter(object,key){
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

export {
  optionFilter,
  optionObjectFilter
}