<template>
  <div class="app-container">
    <!--用户数据-->
    <el-row class="mb8">
      <el-col>
        <span style="margin-right: 5px; font-size: 14px">机器人名称</span>
        <el-select
          style="margin-right: 10px"
          v-model="value"
          placeholder="请选择"
          @change="handleNodeClick"
        >
          <el-option
            v-for="item in deptOptions"
            :key="item.id"
            :label="item.bizName"
            :value="item.bizNo"
          >
          </el-option>
        </el-select>
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:user:export']"
          >导出</el-button
        >
      </el-col>
    </el-row>

    <el-table
      v-loading="loading"
      :data="userList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column
        label="任务名称"
        align="center"
        key="batchName"
        prop="batchName"
      />
      <el-table-column
        label="任务描述"
        align="center"
        key="batchInfoDesc"
        prop="batchInfoDesc"
      />
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { listExport, getTaskList } from "@/api/outbound/export_2";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "User",
  dicts: ["sys_normal_disable", "sys_user_sex"],
  components: { Treeselect },
  data() {
    return {
      // 遮罩层
      loading: false,
      // 任务列表数据
      userList: null,
      // 部门树选项
      deptOptions: undefined,
      defaultProps: {
        children: "children",
        label: "label",
      },
      value: "", //机器人id
      ids: [], //任务id列表
    };
  },
  created() {
    this.getTreeselect(); //获取机器人列表
  },
  methods: {
    // 节点单击事件
    handleNodeClick() {
      this.getList(this.value); //获取任务列表
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id).join(",");
    },
    /** 查询任务列表 */
    getList(id) {
      this.loading = true;
      getTaskList(id).then((res) => {
        this.userList = res.data;
        this.loading = false;
      });
    },
    /** 查询机器人列表 */
    getTreeselect() {
      listExport().then((res) => {
        this.deptOptions = res.data.map((item) => {
          return {
            bizName: item.bizName,
            bizNo: item.bizNo,
            id: item.id,
          };
        });
        this.value = this.deptOptions[0].bizNo;
        this.getList(this.value);
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      //this.value 机器人id
      //this.ids 任务列表id
      this.download(
        "/outbound/labels/export?robotId=" +
          this.value +
          "&batchIds=" +
          this.ids,
        {},
        `分析报告${new Date().getTime()}.xlsx`
      );
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
  },
};
</script>
