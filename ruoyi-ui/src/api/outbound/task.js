import request from '@/utils/request'

export function getmessageslist(params,pageIndex,page_size) {
  return request({
    url: '/message/list',
    method: 'get',
    params: {
      taskId: params.id,
      pageIndex:  pageIndex,
      pageSize: page_size
    }
  })
}

// 查询任务列列表
export function listTask(query) {
  return request({
    url: '/outbound/task/list',
    method: 'get',
    params: query
  })
}

// 查询任务列详细
export function getTask(id) {
  return request({
    url: '/outbound/task/' + id,
    method: 'get'
  })
}

// 新增任务列
export function addTask(data) {
  return request({
    url: '/outbound/task',
    method: 'post',
    data: data
  })
}

// 修改任务列
export function updateTask(data) {
  return request({
    url: '/outbound/task',
    method: 'put',
    data: data
  })
}

// 删除任务列
export function delTask(id) {
  return request({
    url: '/outbound/task/' + id,
    method: 'delete'
  })
}
