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
        <th>章节</th>
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

            <button v-on:click="edit(chapter)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button v-on:click="del(chapter.id)" class="btn btn-xs btn-danger">
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
            <h4 class="modal-title">添加章节</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">课程</label>
                <div class="col-sm-10">
                  <input v-model="chapter.name" class="form-control" placeholder="请输入课程">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">课程编号</label>
                <div class="col-sm-10">
                  <input v-model="chapter.courseId" class="form-control" placeholder="请输入课程编号">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">添加</button>
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
        chapters: []
      }
    },
    mounted() {
      let _this = this;
      _this.list(1);
    },
    methods: {
      del(id) {
        let _this = this;
        Confirm.show("删除","删除章节将不可恢复",function () {
          Loading.show();
          _this.$ajax.delete("http://localhost:9000/business/admin/chapter/delete/" + id).then((respond) => {
            let resp = respond.data;
            console.log("删除结果：{}", respond);
            if (resp.success) {
              _this.list(_this.$refs.pagination.page);
              Loading.hide();
              toast.success("删除成功");
            } else {
              toast.error("删除失败");
            }
          });
        })
        // Swal.fire({
        //   title: '确认删除?',
        //   text: "你将删除该章节!",
        //   icon: 'warning',
        //   showCancelButton: true,
        //   confirmButtonColor: '#3085d6',
        //   cancelButtonColor: '#d33',
        //   confirmButtonText: '确认',
        //   cancelButtonText: '取消'
        // }).then((result) => {
        //   if (result.isConfirmed) {
        //
        //   }
        // })

      },
      edit(chapter) {
        let _this = this;
        _this.chapter = $.extend({}, chapter);
        $("#form-modal").modal("show")
      },
      save() {
        let _this = this;
        Loading.show();
        _this.$ajax.post("http://localhost:9000/business/admin/chapter/save", _this.chapter)
          .then((respond) => {
            let resp = respond.data;
            console.log("保存章节结果{}", resp);
            if (resp.success) {
              this.list(_this.$refs.pagination.page);
              Loading.hide();
              $("#form-modal").modal("hide");
              toast.success("保存成功");
            } else {
              toast.error("保存失败");
            }
          })
      },
      add() {
        let _this = this;
        _this.chapter = {};
        $("#form-modal").modal("show")
      },
      list(page) {
        let _this = this;
        _this.$ajax.post("http://localhost:9000/business/admin/chapter/list", {
          page: page,
          size: _this.$refs.pagination.size
        }).then((respond) => {
          console.log(respond)
          let resp = respond.data;
          _this.chapters = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      }
    }
  }
</script>

<style scoped>

</style>