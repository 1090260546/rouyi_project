import request from '@/utils/request'

// 查询短信发送记录列表
export function listSendMessage(query) {
  return request({
    url: '/outbound/sendMessage/list',
    method: 'get',
    params: query
  })
}

// 查询短信发送记录详细
export function getSendMessage(id) {
  return request({
    url: '/outbound/sendMessage/' + id,
    method: 'get'
  })
}

// 新增短信发送记录
export function addSendMessage(data) {
  return request({
    url: '/outbound/sendMessage/importData',
    method: 'post',
    data: data
  })
}

// 修改短信发送记录
export function updateSendMessage(data) {
  return request({
    url: '/outbound/sendMessage',
    method: 'put',
    data: data
  })
}

// 删除短信发送记录
export function delSendMessage(id) {
  return request({
    url: '/outbound/sendMessage/' + id,
    method: 'delete'
  })
}
