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

        <!--${tableNameCn}列表-->
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th class="center">
                    <label class="pos-rel">
                        <input type="checkbox" class="ace"/>
                        <span class="lbl"></span>
                    </label>
                </th><#list fieldList as field>
                <#if field.nameHump != "createdAt" && field.nameHump != "updatedAt">
                <th>${field.nameCn}</th></#if></#list>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="${domain} in ${domain}s">
                <td class="center">
                    <label class="pos-rel">
                        <input type="checkbox" class="ace"/>
                        <span class="lbl"></span>
                    </label>
                </td>
                <#list fieldList as field>
                     <#if field.nameHump != "createdAt" && field.nameHump != "updatedAt">
                         <#if field.enums>
                             <td class="center">{{$filters.optionObjectFilter(${field.enumsConst},${domain}.${field.nameHump})}}</td>
                         <#else>
                             <td class="center">{{${domain}.${field.nameHump}}}</td>
                         </#if>
                     </#if>
                </#list>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">

                        <button v-on:click="edit(${domain})" class="btn btn-xs btn-info">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button v-on:click="del(${domain}.id)" class="btn btn-xs btn-danger">
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
                            <#list fieldList as field>
                                <#if field.nameHump != "id" && field.nameHump != "createdAt" && field.nameHump != "updatedAt">
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">${field.nameCn}</label>
                                     <div class="col-sm-10">
                                    <#if field.enums>
                                        <select v-model="${domain}.${field.nameHump}" class="form-control">
                                            <option v-for="opt in ${field.enumsConst}" v-bind:value="opt.key">{{opt.value}}</option>
                                        </select>
                                    <#else>
                                        <input v-model="${domain}.${field.nameHump}" class="form-control"
                                               placeholder="请输入${field.nameCn}">
                                    </#if>
                                    </div>
                                 </div>
                                 </#if>
                            </#list>
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
    name: "${module}-${domain}",
    components: {Pagination},
    data: function () {
      return {
      ${domain}:{},
      ${domain}s: [],
      <#list fieldList as field>
        <#if field.enums>
      ${field.enumsConst}: ${field.enumsConst},
        </#if>
      </#list>

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
        Confirm.show("删除", "删除${tableNameCn}将不可恢复", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + "/business/admin/${domain}/delete/" + id).then((respond) => {
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
      edit(${domain}) {
        let _this = this;
        _this.${domain} = $.extend({}, ${domain});
        $("#form-modal").modal("show")
      },
      /*点击保存*/
      save() {
        let _this = this;
        //保存校验
        if (1 != 1
          <#list fieldList as field >
            <#if field.nameHump != "id" && field.nameHump != "createdAt" && field.nameHump != "updatedAt">
                <#if !field.nullAble>
          || !Validator.require(_this.${domain}.${field.nameHump}, "${field.nameCn}")
                </#if>
            <#if (field.length > 0)>
          || !Validator.length(_this.${domain}.${field.nameHump}, "${field.nameCn}", 1, ${field.length?c})
            </#if>
            </#if>
          </#list>
        ) {
          return
        }
        //加载框显示
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/${domain}/save", _this.${domain})
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
        _this.${domain} = {};
        $("#form-modal").modal("show")
      },
      /*查询${tableNameCn}列表*/
      list(page) {
        let _this = this;
        _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/${domain}/list", {
          page: page,
          size: _this.$refs.pagination.size
        }).then((respond) => {
          let resp = respond.data;
          _this.${domain}s = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      }
    }
  }
</script>

<style scoped>

</style>