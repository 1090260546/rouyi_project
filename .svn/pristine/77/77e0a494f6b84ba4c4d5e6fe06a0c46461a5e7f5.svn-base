<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="任务名称" prop="taskName">
        <el-input
          v-model="queryParams.taskName"
          placeholder="任务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号码" prop="sendMobile">
        <el-input
          v-model="queryParams.sendMobile"
          placeholder="发送手机号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批次号" prop="batchId">
        <el-input
          v-model="queryParams.batchId"
          placeholder="请输入批次号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['outbound:sendMessage:add']"
          >发送短信</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['outbound:sendMessage:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="sendMessageList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务名称" align="center" prop="taskName" />
      <el-table-column
        label="发送时间"
        align="center"
        prop="sendTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.sendTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发送内容" align="center" prop="sendContent" show-overflow-tooltip />
      <el-table-column label="发送手机号" align="center" prop="sendMobile" />
      <el-table-column label="批次号" align="center" prop="batchId" />
      <el-table-column label="发送结果" align="center" prop="result" />
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改短信发送对话框 -->
    <el-dialog class="sendfrom" :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="模板名称" prop="taskName">
          <el-input v-model="form.taskName" placeholder="请输入模板名称" />
        </el-form-item>
        <el-form-item label="模板内容" prop="sendContent">
          <el-input
            v-model="form.sendContent"
            type="textarea"
            placeholder="请输入内容"
            class="outbound_text"
          />
        </el-form-item>
        <el-form-item label="街道名称" prop="tempRegion">
          <el-select
            v-model="form.tempRegion"
            placeholder="请选择街道名称"
            @change="temp_change(form.tempRegion)"
          >
            <el-option
              v-for="dict in dict.type.ob_region_info"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="号码上传" prop="file">
          <el-upload
            class="upload-demo"
            ref="upload"
            :auto-upload="false"
            action
            drag
            multiple
            :on-change="handleChange"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div class="el-upload__tip text-center" slot="tip">
              <span>仅允许导入xls、xlsx格式文件。</span>
              <el-link
                type="primary"
                :underline="false"
                style="font-size: 12px; vertical-align: baseline"
                @click="importTemplate"
                >下载模板</el-link
              >
            </div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm"
          >发 送</el-button
        >
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listSendMessage,
  getSendMessage,
  delSendMessage,
  addSendMessage,
  updateSendMessage,
} from "@/api/outbound/sendMessage";

export default {
  name: "SendMessage",
  dicts: ["ob_region_info"],
  data() {
    return {
      // 按钮loading
      buttonLoading: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 短信发送记录表格数据
      sendMessageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskId: undefined,
        sendMobile: undefined,
        batchId: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        taskName: [
          { required: true, message: "模板名称不能为空", trigger: "blur" },
        ],
        sendContent: [
          { required: true, message: "模板内容不能为空", trigger: "blur" },
        ],
        tempRegion: [
          { required: true, message: "请选择街道名称", trigger: "blur" },
        ],
        file: [{ required: true, message: "请选择文件上传", trigger: "blur" }],
      },
    };
  },
  created() {
    this.getList();
  },
  watch: {
    open(newVal) {
      if (!newVal) {
        this.reset();
        this.$refs.upload.clearFiles();
      }
    },
  },
  methods: {
    temp_change() {},
    /** 下载模板操作 */
    importTemplate() {
      this.download(
        "/outbound/sendMessage/importTemplate",
        {},
        `手机号码模板.xlsx`
      );
    },
    handleChange(file, fileList) {
      console.log(file);
      this.form.file = file.raw;
    },
    /** 查询短信发送记录列表 */
    getList() {
      this.loading = true;
      listSendMessage(this.queryParams).then((response) => {
        this.sendMessageList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        taskName: undefined,
        sendContent: undefined,
        tempRegion: undefined,
        file: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "短信发送";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const id = row.id || this.ids;
      getSendMessage(id).then((response) => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改短信发送";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.id != null) {
            updateSendMessage(this.form)
              .then((response) => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          } else {
            let formData = new FormData();
            formData.append("taskName", this.form.taskName);
            formData.append("sendContent", this.form.sendContent);
            formData.append("regionId", this.form.tempRegion);
            formData.append("file", this.form.file);
            addSendMessage(formData)
              .then((response) => {
                this.$modal.msgSuccess("发送成功");
                this.open = false;
                this.getList();
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除短信发送记录编号为"' + ids + '"的数据项？')
        .then(() => {
          this.loading = true;
          return delSendMessage(ids);
        })
        .then(() => {
          this.loading = false;
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .finally(() => {
          this.loading = false;
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "outbound/sendMessage/export",
        {
          ...this.queryParams,
        },
        `sendMessage_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>

<style>
.outbound_text .el-textarea__inner {
  height: 200px !important;
}
.sendfrom .text-center {
  text-align: left !important;
}
</style>
