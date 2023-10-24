<template>
  <div class="row">
    <!-- PAGE CONTENT BEGINS -->
    <div class="row">
      <div class="col-xs-12">
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
          <tr>
            <td class="center">
              <label class="pos-rel">
                <input type="checkbox" class="ace"/>
                <span class="lbl"></span>
              </label>
            </td>

            <td class="center">0005</td>
            <td class="center">8886</td>
            <td class="center">章节1</td>

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
      </div><!-- /.span -->
    </div><!-- /.row -->
  </div><!-- /.row -->
</template>

<script>
  export default {
    name: "chapter",
    data: function () {
      return {
        chapters: []
      }
    },
    mounted() {
      let _this = this;
      _this.list();
    },
    methods: {
      list() {
        let _this = this;
        _this.$ajax.get("http://localhost:9000/business/admin/chapter/list").then((respond) => {
          _this.chapters = respond.data;
          console.log(respond)
        })
      }
    }
  }
</script>

<style scoped>

</style>