import request from '@/utils/request'

// 查询机器人标签管理列表
export function listExport() {
  return request({
    url: '/outbound/export/getRobotList',
    method: 'get'
  })
}

// 查询机器人标签管理详细
export function getExport(id) {
  return request({
    url: '/outbound/export/' + id,
    method: 'get'
  })
}

// 新增机器人标签管理
export function addExport(data) {
  return request({
    url: '/outbound/export',
    method: 'post',
    data: data
  })
}

// 修改机器人标签管理
export function updateExport(data) {
  return request({
    url: '/outbound/export',
    method: 'put',
    data: data
  })
}

// 删除机器人标签管理
export function delExport(id) {
  return request({
    url: '/outbound/export/' + id,
    method: 'delete'
  })
}


export function getTaskList(data) {
  return request({
    url: '/outbound/export/getTaskList',
    method: 'get',
    params: {
      bizNo: data
    }
  })
}