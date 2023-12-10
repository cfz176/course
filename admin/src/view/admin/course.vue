<template>
    <div>
      <!--顶部控件-->
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

      <!--课程展示-->
      <div class="row">
        <div v-for="course in courses" class="col-md-4">
          <div class="thumbnail search-thumbnail">
            <img v-show="!course.image" class="media-object" src="/static/image/courseImg.jpg" />
            <img v-show="course.image" class="media-object" src="{{course.image}}" />
            <div class="caption">
              <div class="clearfix">
                <span class="pull-right label label-primary info-label">
                   {{$filters.optionObjectFilter(COURSE_LEVEL,course.level)}}
                </span>
                <span class="pull-right label label-primary info-label">
                  {{$filters.optionObjectFilter(COURSE_CHARGE,course.charge)}}
                </span>
                <span class="pull-right label label-primary info-label">
                  {{$filters.optionObjectFilter(COURSE_STATUS,course.status)}}
                </span>
              </div>

              <h3 class="search-title">
                <a href="#" class="blue">{{course.name}}</a>
              </h3>
              <p>
                <span class="blue bolder bigger-150">{{course.price}}&nbsp;<i class="fa fa-rmb"></i></span>
              </p>
              <p>{{course.summary}}</p>
              <p>
                <span class="badge badge-danger">时长：{{$filters.formatSecond(course.time)}}</span>
              </p>
              <p>
                <button v-on:click="toChapter(course)" class="btn btn-white btn-info btn-bold btn-round" >
                  大章
                </button>
                <button v-on:click="edit(course)" class="btn btn-white btn-info btn-bold btn-round" >
                  修改
                </button>
                <button v-on:click="del(course.id)" class="btn btn-white btn-warning btn-round" >
                  删除
                </button>
              </p>
            </div>
          </div>
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
                            <label class="col-sm-2 control-label">分类</label>
                            <div class="col-sm-10">
                              <ul id="tree" class="ztree"></ul>
                            </div>
                          </div>
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
                                     <label class="col-sm-2 control-label">顺序</label>
                                     <div class="col-sm-10">
                                        <input v-model="course.sort" class="form-control"
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
      _this.list(1)
      //初始化分类树
      _this.initTree();
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
      },
      /*跳转指定课程章节*/
      toChapter(course) {
        let _this = this;
        SessionStorage.set("course", course);
        _this.$router.push("/business/chapter");
      },
      /*初始化分类树*/
      initTree(){
        var setting = {
          check: {
            enable: true
          },
          data: {
            simpleData: {
              enable: true
            }
          }
        };

        var zNodes =[
          { id:1, pId:0, name:"随意勾选 1", open:true},
          { id:11, pId:1, name:"随意勾选 1-1", open:true},
          { id:111, pId:11, name:"随意勾选 1-1-1"},
          { id:112, pId:11, name:"随意勾选 1-1-2"},
          { id:12, pId:1, name:"随意勾选 1-2", open:true},
          { id:121, pId:12, name:"随意勾选 1-2-1"},
          { id:122, pId:12, name:"随意勾选 1-2-2"},
          { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
          { id:21, pId:2, name:"随意勾选 2-1"},
          { id:22, pId:2, name:"随意勾选 2-2", open:true},
          { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
          { id:222, pId:22, name:"随意勾选 2-2-2"},
          { id:23, pId:2, name:"随意勾选 2-3"}
        ];

          $.fn.zTree.init($("#tree"), setting, zNodes);
      }
    }
  }
</script>

<style scoped>
  .caption h3{
    font-size: 20px;
  }
</style>