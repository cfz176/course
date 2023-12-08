<template>
    <div>

        <div id="row">
            <div class="col-md-6">

                <p>
                    <button v-on:click="list()" class="btn btn-white btn-info  btn-round">
                        <i class="ace-icon fa fa-refresh bigger-120 blue"></i>
                        刷新
                    </button>
                    &nbsp;
                    <button v-on:click="add()" class="btn btn-white btn-info  btn-round">
                        <i class="ace-icon fa fa-edit bigger-120 blue"></i>
                        新增
                    </button>
                </p>

                <!--分类列表-->
                <table id="level1-table" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center">id</th>
                        <th class="center">名称</th>
                        <th class="center">排序</th>
                        <th class="center">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="category in level1" v-on:click="showLevel2(category)"  v-bind:class="{'active' : category.id === active.id}">
                        <td class="center">{{category.id}}</td>
                        <td class="center">{{category.name}}</td>
                        <td class="center">{{category.sort}}</td>
                        <td class="center">
                            <div class="hidden-sm hidden-xs btn-group">

                                <button v-on:click="edit(category)" class="btn btn-xs btn-info">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
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

            </div>
            <div class="col-md-6">
                <p>
                    <button v-on:click="add()" class="btn btn-white btn-info  btn-round">
                        <i class="ace-icon fa fa-edit bigger-120 blue"></i>
                        新增
                    </button>
                </p>

                <!--分类列表-->
                <table id="level2-table" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center">id</th>
                        <th class="center">名称</th>
                        <th class="center">排序</th>
                        <th class="center">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                      <tr v-for="category in level2">
                        <td class="center">{{category.id}}</td>
                        <td class="center">{{category.name}}</td>
                        <td class="center">{{category.sort}}</td>
                        <td class="center">
                            <div class="hidden-sm hidden-xs btn-group">

                                <button v-on:click="edit(category)" class="btn btn-xs btn-info">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
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

            </div>
        </div>

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
                                     <label class="col-sm-2 control-label">上级id</label>
                                     <div class="col-sm-10">
                                        <input v-model="category.parent" class="form-control"
                                               placeholder="请输入上级id">
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">名称</label>
                                     <div class="col-sm-10">
                                        <input v-model="category.name" class="form-control"
                                               placeholder="请输入名称">
                                    </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">排序</label>
                                     <div class="col-sm-10">
                                        <input v-model="category.sort" class="form-control"
                                               placeholder="请输入排序">
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
    name: "businesss-category",
    components: {Pagination},
    data: function () {
      return {
      categorys: {},
      category: [],
      level1: [],
      level2: [],
      active: [],
    }
    },
    mounted() {
      let _this = this;
      _this.list();
    },
    methods: {
      /*点击删除*/
      del(id) {
        let _this = this;
        Confirm.show("删除", "删除分类将不可恢复", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + "/business/admin/category/delete/" + id).then((respond) => {
            let resp = respond.data;
            if (resp.success) {
              _this.list();
              Loading.hide();
              Toast.success("删除成功");
            } else {
              Toast.error("删除失败");
            }
          });
        })

      },
      /*点击弹出修改框*/
      edit(category) {
        let _this = this;
        _this.category = $.extend({}, category);
        $("#form-modal").modal("show")
      },
      /*点击保存*/
      save() {
        let _this = this;
        //保存校验
        if (1 != 1
          || !Validator.require(_this.category.parent, "上级id")
          || !Validator.require(_this.category.name, "名称")
          || !Validator.length(_this.category.name, "名称", 1, 50)
        ) {
          return
        }
        //加载框显示
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/category/save", _this.category)
          .then((respond) => {
            let resp = respond.data;
            if (resp.success) {
              this.list();
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
        _this.category = {};
        $("#form-modal").modal("show")
      },
      /*查询分类列表*/
      list() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + "/business/admin/category/list").then((respond) => {
            _this.categorys = respond.data.content
            //格式化为树结构
            _this.level1 = []
            for (let i = 0; i < _this.categorys.length; i++) {
                let parent = _this.categorys[i];
                //'000000'为顶级分类
                if (parent.parent === '000000') {
                    _this.level1.push(parent);
                    //查找子分类
                    for (let j = 0; j < _this.categorys.length; j++) {
                        let child = _this.categorys[j];
                        if (child.parent === parent.id) {
                            //只在第一次初始化child数组
                            if (Tool.isEmpty(parent.child)) {
                                parent.child = [];
                            }
                            //将子类放入父级下
                            parent.child.push(child);
                        }
                    }
                }
            }
        })
      },
      //展示子分类
      showLevel2(category) {
        let _this = this;
        _this.active = category;
        _this.level2 = category.child;
      }
    }
  }
</script>
<style scoped>
  .active td{
    background-color: lightgray !important;
  }
</style>