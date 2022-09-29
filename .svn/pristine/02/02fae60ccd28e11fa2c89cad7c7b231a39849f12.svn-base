import request from '@/utils/request'

// 查询机器人标签配置列表
export function listLabels(id) {
  return request({
    url: '/outbound/labels/list',
    method: 'get',
    params: {
      bizNo: id
    }
  })
}

// 查询机器人标签配置详细
export function getLabels(id) {
  return request({
    url: '/outbound/labels/getExportLabels',
    method: 'get',
    params: {
      id: id
    }
  })
}

// 新增机器人标签配置
export function addLabels(data) {
  return request({
    url: '/outbound/labels/add',
    method: 'post',
    data: data
  })
}

// 修改机器人标签配置
export function updateLabels(data) {
  return request({
    url: '/outbound/labels/edit',
    method: 'post',
    data: data
  })
}

// 删除机器人标签配置
export function delLabels(data) {
  return request({
    url: '/outbound/labels/remove/' + data,
    method: 'post'
  })
}
