<template>
    <div>
        <p>
            <button v-on:click="list(1)" class="btn btn-white btn-info  btn-round">
                <i class="ace-icon fa fa-refresh bigger-120 blue"></i>
                刷新
            </button>
            &nbsp;
            <button v-on:click="add()" class="btn btn-white btn-info  btn-round">
                <i class="ace-icon fa fa-edit bigger-120 blue"></i>
                新增
            </button>
        </p>

        <!--课程列表-->
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th class="center">
                    <label class="pos-rel">
                        <input type="checkbox" class="ace"/>
                        <span class="lbl"></span>
                    </label>
                </th>
                <th>id</th>
                <th>名称</th>
                <th>概述</th>
                <th>时长</th>
                <th>价格</th>
                <th>封面</th>
                <th>级别</th>
                <th>收费</th>
                <th>状态</th>
                <th>报名数</th>
                <th>顺序</th>
                <th>创建时间</th>
                <th>修改时间</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="course in courses">
                <td class="center">
                    <label class="pos-rel">
                        <input type="checkbox" class="ace"/>
                        <span class="lbl"></span>
                    </label>
                </td>
                             <td class="center">{{course.id}}</td>
                             <td class="center">{{course.name}}</td>
                             <td class="center">{{course.summary}}</td>
                             <td class="center">{{course.time}}</td>
                             <td class="center">{{course.price}}</td>
                             <td class="center">{{course.image}}</td>
                             <td class="center">{{$filters.optionObjectFilter(COURSE_LEVEL,course.level)}}</td>
                             <td class="center">{{$filters.optionObjectFilter(COURSE_CHARGE,course.charge)}}</td>
                             <td class="center">{{$filters.optionObjectFilter(COURSE_STATUS,course.status)}}</td>
                             <td class="center">{{course.enroll}}</td>
                             <td class="center">{{course.sort}}</td>
                             <td class="center">{{course.createAt}}</td>
                             <td class="center">{{course.updateAt}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">

                        <button v-on:click="edit(course)" class="btn btn-xs btn-info">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button v-on:click="del(course.id)" class="btn btn-xs btn-danger">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>

                    </div>

                    <div class="hidden-md hidden-lg">
                        <div class="inline pos-rel">
                            <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"
                                    data-position="auto">
                                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                            </button>

                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                <li>
                                    <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>

        <!--分页插件-->
        <p>
            <Pagination ref="pagination" v-bind:list="list"></Pagination>
        </p>

        <!--新增模态框-->
        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">添加课程</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">名称</label>
                                     <div class="col-sm-10">
                                        <input v-model="course.name" class="form-control"
                                               placeholder="请输入名称">
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">概述</label>
                                     <div class="col-sm-10">
                                        <input v-model="course.summary" class="form-control"
                                               placeholder="请输入概述">
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">时长</label>
                                     <div class="col-sm-10">
                                        <input v-model="course.time" class="form-control"
                                               placeholder="请输入时长">
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">价格</label>
                                     <div class="col-sm-10">
                                        <input v-model="course.price" class="form-control"
                                               placeholder="请输入价格">
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">封面</label>
                                     <div class="col-sm-10">
                                        <input v-model="course.image" class="form-control"
                                               placeholder="请输入封面">
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">级别</label>
                                     <div class="col-sm-10">
                                        <select v-model="course.level" class="form-control">
                                            <option v-for="opt in COURSE_LEVEL" v-bind:value="opt.key">{{opt.value}}</option>
                                        </select>
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">收费</label>
                                     <div class="col-sm-10">
                                        <select v-model="course.charge" class="form-control">
                                            <option v-for="opt in COURSE_CHARGE" v-bind:value="opt.key">{{opt.value}}</option>
                                        </select>
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">状态</label>
                                     <div class="col-sm-10">
                                        <select v-model="course.status" class="form-control">
                                            <option v-for="opt in COURSE_STATUS" v-bind:value="opt.key">{{opt.value}}</option>
                                        </select>
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">报名数</label>
                                     <div class="col-sm-10">
                                        <input v-model="course.enroll" class="form-control"
                                               placeholder="请输入报名数">
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">顺序</label>
                                     <div class="col-sm-10">
                                        <input v-model="course.sort" class="form-control"
                                               placeholder="请输入顺序">
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">创建时间</label>
                                     <div class="col-sm-10">
                                        <input v-model="course.createAt" class="form-control"
                                               placeholder="请输入创建时间">
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">修改时间</label>
                                     <div class="col-sm-10">
                                        <input v-model="course.updateAt" class="form-control"
                                               placeholder="请输入修改时间">
                                    </div>
                                 </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </div><!-- /.span -->
</template>

<script>
  import Pagination from "../../components/pagination";

  export default {
    name: "businesss-course",
    components: {Pagination},
    data: function () {
      return {
      course:{},
      courses: [],
      COURSE_LEVEL: COURSE_LEVEL,
      COURSE_CHARGE: COURSE_CHARGE,
      COURSE_STATUS: COURSE_STATUS,

    }
    },
    mounted() {
      let _this = this;
      _this.list(1);
    },
    methods: {
      /*点击删除*/
      del(id) {
        let _this = this;
        Confirm.show("删除", "删除课程将不可恢复", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + "/business/admin/course/delete/" + id).then((respond) => {
            let resp = respond.data;
            if (resp.success) {
              _this.list(_this.$refs.pagination.page);
              Loading.hide();
              Toast.success("删除成功");
            } else {
              Toast.error("删除失败");
            }
          });
        })

      },
      /*点击弹出修改框*/
      edit(course) {
        let _this = this;
        _this.course = $.extend({}, course);
        $("#form-modal").modal("show")
      },
      /*点击保存*/
      save() {
        let _this = this;
        //保存校验
        if (1 != 1
          || !Validator.require(_this.course.name, "名称")
          || !Validator.length(_this.course.name, "名称", 1, 50)
          || !Validator.length(_this.course.summary, "概述", 1, 2000)
          || !Validator.length(_this.course.image, "封面", 1, 200)
          || !Validator.length(_this.course.level, "级别", 1, 100)
        ) {
          return
        }
        //加载框显示
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/course/save", _this.course)
          .then((respond) => {
            let resp = respond.data;
            if (resp.success) {
              this.list(_this.$refs.pagination.page);
              $("#form-modal").modal("hide");
              Toast.success("保存成功");
            } else {
              Toast.error(resp.message);
            }
            //加载框关闭
            Loading.hide();
          })
      },
      /*点击弹出新增框*/
      add() {
        let _this = this;
        _this.course = {};
        $("#form-modal").modal("show")
      },
      /*查询课程列表*/
      list(page) {
        let _this = this;
        _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/course/list", {
          page: page,
          size: _this.$refs.pagination.size
        }).then((respond) => {
          let resp = respond.data;
          _this.courses = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      }
    }
  }
</script>

<style scoped>

</style>