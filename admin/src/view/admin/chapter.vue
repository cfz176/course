<template>
  <div>
    <h3 class="blue">
      <router-link to="/business/course">
        <i class="ace-icon fa fa-arrow-left">&nbsp;{{course.name}}课程</i>
      </router-link>
    </h3>
    <hr>
    <p>
      <router-link to="/business/course" class="btn btn-white btn-info  btn-round">
        <i class="ace-icon fa fa-arrow-circle-left"></i>
        选择课程
      </router-link>
      &nbsp;
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

    <!--章节列表-->
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th class="center">
          <label class="pos-rel">
            <input type="checkbox" class="ace"/>
            <span class="lbl"></span>
          </label>
        </th>
        <th class="detail-col">ID</th>
        <th>课程编号</th>
        <th>课程名</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="chapter in chapters">
        <td class="center">
          <label class="pos-rel">
            <input type="checkbox" class="ace"/>
            <span class="lbl"></span>
          </label>
        </td>

        <td class="center">{{chapter.id}}</td>
        <td class="center">{{chapter.courseId}}</td>
        <td class="center">{{chapter.name}}</td>

        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="toSection(chapter)" name="tButton" class="btn btn-white btn-info btn-bold btn-round" >
              小节
            </button>
            <button v-on:click="edit(chapter)" name="tButton" class="btn btn-white btn-info btn-bold btn-round" >
              修改
            </button>
            <button v-on:click="del(chapter.id)" name="tButton" class="btn btn-white btn-warning btn-round" >
              删除
            </button>
          </div>

          <div class="hidden-md hidden-lg">
            <div class="inline pos-rel">
              <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"
                      data-position="auto">
                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
              </button>

              <ul
                class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
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
                <label class="col-sm-2 control-label">章节名</label>
                <div class="col-sm-10">
                  <input v-model="chapter.name" class="form-control" placeholder="请输入课程">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">所属课程</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{course.name}}</p>
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
    name: "chapter",
    components: {Pagination},
    data: function () {
      return {
        chapter: {},
        chapters: [],
        course: {}
      }
    },
    mounted() {
      let _this = this;
      var course = SessionStorage.get("course") || {};
      if (Tool.isEmpty(course)) {
        _this.$router.push('/business/course');
        Confirm.showRouter('请先选择课程','',function () {
        })
        return
      }
      _this.course = course;
      _this.list(1);
    },
    methods: {
      /*点击删除*/
      del(id) {
        let _this = this;
        Confirm.show("删除", "删除章节将不可恢复", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + "/business/admin/chapter/delete/" + id).then((respond) => {
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
      edit(chapter) {
        let _this = this;
        _this.chapter = $.extend({}, chapter);
        $("#form-modal").modal("show")
      },
      /*跳转到指定小节*/
      toSection(chapter) {
        let _this = this;
        SessionStorage.set("chapter", chapter);
        _this.$router.push("/business/section");
      },
      /*点击保存*/
      save() {
        let _this = this;

        //保存校验
        if (!Validator.require(_this.chapter.name, "课程名")
          || !Validator.length(_this.chapter.courseId, "课程号", 4, 8)) {
          return
        }

        _this.chapter.courseId = _this.course.id

        //加载框显示
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/chapter/save", _this.chapter)
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
        _this.chapter = {};
        $("#form-modal").modal("show")
      },
      /*查询章节列表*/
      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/chapter/list", {
          page: page,
          size: _this.$refs.pagination.size,
          courseId: _this.course.id,
        }).then((respond) => {
          let resp = respond.data;
          _this.chapters = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
          Loading.hide();
        })
      }
    }
  }
</script>

<style scoped>
  h3{
    margin-top: 0px;
  }
  hr{
    margin-top: 5px;
    margin-bottom: 10px;
  }
</style>