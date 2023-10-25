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
            <button class="btn btn-xs btn-success">
              <i class="ace-icon fa fa-check bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-warning">
              <i class="ace-icon fa fa-flag bigger-120"></i>
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
                  <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																			<span class="blue">
																				<i class="ace-icon fa fa-search-plus bigger-120"></i>
																			</span>
                  </a>
                </li>

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
    <div class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加章节</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                  <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                </div>
              </div>
              <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-primary">添加</button>
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
        chapters: []
      }
    },
    mounted() {
      let _this = this;
      _this.list(1);
    },
    methods: {
      add() {
        $(".modal").modal("show")
      },
      list(page) {
        let _this = this;
        _this.$ajax.post("http://localhost:9000/business/admin/chapter/list", {
          page: page,
          size: _this.$refs.pagination.size
        }).then((respond) => {
          console.log(respond)
          _this.chapters = respond.data.list;
          _this.$refs.pagination.render(page,respond.data.total);
        })
      }
    }
  }
</script>

<style scoped>

</style>