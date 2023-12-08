<template>
  <div>
    <h3 class="blue">
      <router-link to="/business/course">
        <i class="ace-icon fa fa-arrow-left">&nbsp;{{course.name}}课程：</i>
      </router-link>
      <router-link to="/business/chapter">
        <i class="ace-icon fa fa-arrow-left">&nbsp;{{chapter.name}}章节</i>
      </router-link>
    </h3>
    <hr>
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

    <!--分页插件-->
    <p>
      <Pagination ref="pagination" v-bind:list="list"></Pagination>
    </p>

    <!--小节列表-->
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th class="center">
          <label class="pos-rel">
            <input type="checkbox" class="ace"/>
            <span class="lbl"></span>
          </label>
        </th>
        <th>ID</th>
        <th>标题</th>
        <th>课程</th>
        <th>章节</th>
        <th>视频</th>
        <th>时长</th>
        <th>收费</th>
        <th>顺序</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="section in sections">
        <td class="center">
          <label class="pos-rel">
            <input type="checkbox" class="ace"/>
            <span class="lbl"></span>
          </label>
        </td>
        <td class="center">{{section.id}}</td>
        <td class="center">{{section.title}}</td>
        <td class="center">{{section.courseId}}</td>
        <td class="center">{{section.chapterId}}</td>
        <td class="center">{{section.video}}</td>
        <td class="center">{{section.time}}</td>
        <td class="center">{{$filters.optionObjectFilter(SECTION_CHARGE,section.charge)}}</td>
        <td class="center">{{section.sort}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">

            <button v-on:click="edit(section)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button v-on:click="del(section.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
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
                <label class="col-sm-2 control-label">标题</label>
                <div class="col-sm-10">
                  <input v-model="section.title" class="form-control"
                         placeholder="请输入标题">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">课程</label>
                <div class="col-sm-10">
                  {{course.name}}
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">章节</label>
                <div class="col-sm-10">
                  {{chapter.name}}
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">视频</label>
                <div class="col-sm-10">
                  <input v-model="section.video" class="form-control"
                         placeholder="请输入视频">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">时长</label>
                <div class="col-sm-10">
                  <input v-model="section.time" class="form-control"
                         placeholder="请输入时长">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">收费</label>
                <div class="col-sm-10">
                  <select v-model="section.charge" class="form-control">
                    <option v-for="opt in SECTION_CHARGE" v-bind:value="opt.key">{{opt.value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">顺序</label>
                <div class="col-sm-10">
                  <input v-model="section.sort" class="form-control"
                         placeholder="请输入顺序">
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
    name: "business-section",
    components: {Pagination},
    data: function () {
      return {
        section: {},
        sections: [],
        SECTION_CHARGE: SECTION_CHARGE,
        chapter: {},
        course: {}
      }
    },
    mounted() {
      let _this = this;
      var course = SessionStorage.get("course") || {};
      var chapter = SessionStorage.get("chapter") || {};
      if (Tool.isEmpty(course)) {
        _this.$router.push('/business/course');
          Confirm.showRouter('请先选择课程','',function () {
          })
          return
      }
      if (Tool.isEmpty(chapter)) {
        _this.$router.push('/business/chapter');
          Confirm.showRouter('请先选择章节','',function () {
          })
        return
      }
      _this.chapter = chapter;
      _this.course = course;
      _this.list(1);
    },
    methods: {
      /*点击删除*/
      del(id) {
        let _this = this;
        Confirm.show("删除", "删除小节将不可恢复", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + "/business/admin/section/delete/" + id).then((respond) => {
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
      edit(section) {
        let _this = this;
        _this.section = $.extend({}, section);
        $("#form-modal").modal("show")
      },
      /*点击保存*/
      save() {
        let _this = this;
        //保存校验
        if (1 != 1
          || !Validator.require(_this.section.title, "标题")
          || !Validator.length(_this.section.title, "标题", 1, 50)
          || !Validator.length(_this.section.video, "视频", 1, 200)
        ) {
          return
        }

        _this.section.courseId = _this.course.id
        _this.section.chapterId = _this.chapter.id

        //加载框显示

        _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/section/save", _this.section)
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
        _this.section = {};
        $("#form-modal").modal("show")
      },
      /*查询小节列表*/
      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/section/list", {
          page: page,
          size: _this.$refs.pagination.size,
          chapterId: _this.chapter.id
        }).then((respond) => {
          let resp = respond.data;
          Loading.hide();
          _this.sections = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
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