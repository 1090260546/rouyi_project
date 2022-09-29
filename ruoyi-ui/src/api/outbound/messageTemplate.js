import request from '@/utils/request'

// 查询短信发送模板列表
export function listMessageTemplate(query) {
  return request({
    url: '/outbound/messageTemplate/list',
    method: 'get',
    params: query
  })
}

// 获取任务名称列表
export function getrwlist(data) {
  return request({
    url:'/outbound/messageTemplate/taskList',
    method: 'get',
    params: data
  })
}

// 获取任务标签列表
export function gettempLabe(data) {
  return request({
    url:'/outbound/messageTemplate/getTaskLabel',
    method: 'get',
    params: data
  })
}


// 查询短信发送模板详细
export function getMessageTemplate(id) {
  return request({
    url: '/outbound/messageTemplate/' + id,
    method: 'get'
  })
}

// 新增短信发送模板
export function addMessageTemplate(data) {
  return request({
    url: '/outbound/messageTemplate',
    method: 'post',
    data: data
  })
}

// 修改短信发送模板
export function updateMessageTemplate(data) {
  return request({
    url: '/outbound/messageTemplate',
    method: 'post',
    data: data
  })
}

// 删除短信发送模板
export function delMessageTemplate(id) {
  return request({
    url: '/outbound/messageTemplate/' + id,
    method: 'delete'
  })
}
