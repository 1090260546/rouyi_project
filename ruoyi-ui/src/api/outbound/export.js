import request from '@/utils/request'

// 查询机器人标签管理列表
export function listExport(query) {
  return request({
    url: '/outbound/export/list',
    method: 'get',
    params: query
  })
}

// 获取机器人标签列表
export function getExportList(id) {
  return request({
    url: '/outbound/export/getRobotLabel',
    method: 'get',
    params: {
      bizNo: id
    }
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
