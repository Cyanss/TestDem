<template>
  <div class="plugin-show" v-if="pageData!=null">
    <div class="tool">
      <div class="tools-wrapper">
        <span class="add-bt title-bt" @click="addInfo">增加</span>
        <span class="delete-bt title-bt" @click="deleteInfo">删除</span>
        <span class="update-bt title-bt" @click="updateInfo">修改</span>
        <span class="detail-bt title-bt" @click="detailInfo">详细</span>
      </div>
      <div class="tip">
        <span class="tip-text">{{tipText}}</span>
      </div>
    </div>
    <div class="info">
      <ul class="info-list"  v-for="(pageItem, pageIndex) in pageData.list" :key="pageIndex">
        <li class="info-item" :class="currentIndex === pageIndex ? 'select-item' : 'default-item' " @click="setCurrentItem(pageIndex)">
          <span class="info-index">{{pageIndex + pageNumber + 1}}</span>
          <img width="50" height="50" class="icon" :src="pageItem.icon">
          <span class="userid">{{pageItem.userid}}</span>
          <span class="pluginname">{{pageItem.pluginname}}</span>
          <span class="type">{{pageItem.type}}</span>
          <span class="env">{{pageItem.env}}</span>
          <span class="keyword">{{pageItem.keyword}}</span>
          <span class="url">{{pageItem.pluginUrl}}</span>
          <span class="content">{{pageItem.content}}</span>
          <span class="createtime">{{pageItem.createtime}}</span>
        </li>
      </ul>
    </div>
    <div class="page-wrapper">
      <div class="page">
        <span class="total">共{{pageData.pages}}页</span>
        <span class="first icon-first bt" @click="pageFirst"></span>
        <span class="prv icon-previous bt" @click="pagePrv"></span>
        <span class="current">第{{currentPage}}页</span>
        <span class="next icon-next bt" @click="pageNext"></span>
        <span class="last icon-last bt" @click="pageLast"></span>
        <span class="refresh icon-refresh bt" @click="pageRefresh"></span>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import pluginApi from 'src/api/plugin';
  export default {
    props: {
      pageSize: {
        type: Number,
        default: 10
      }
    },
    inject: ['reload'],
    data() {
      return {
        currentPage: 1,
        pageData: null,
        currentIndex: -1,
        currentPluginId: -1,
        pageNumber: 0,
        tipText: '',
        datas: null
      };
    },
    mounted() {
      if (this.pageData === null) {
        this.__getPluginInfo(this.currentPage, this.pageSize);
      }
    },
    methods: {
      setTipText(msg) {
        this.tipText = msg;
        setTimeout(() => {
          this.tipText = '';
        }, 3000);
      },
      __getPluginInfo(page, size) {
        pluginApi.getPluginInfo(page, size).then((res) => {
          if (res.code === 0) {
            this.pageData = res.data;
            // this.tipText = res.msg;
            console.log(res.data);
          }
        }).catch((error) => {
          console.log(error);
          this.setTipText(error.msg);
        });
      },
      setCurrentItem(index) {
        this.currentIndex = index;
        this.currentPluginId = this.pageData.list[index].pluginid;
      },
      addInfo() {
        this.$emit('jump', 1, '/plugin/input');
      },
      deleteInfo() {
        if (this.currentPluginId !== -1) {
          pluginApi.deletePluginInfo(this.currentPluginId).then((res) => {
            this.setTipText(res.msg);
            this.__getPluginInfo(this.currentPage, this.pageSize);
            this.reload();
          }).catch((error) => {
            this.setTipText(error.msg);
          });
        } else {
          this.setTipText('请选择要删除的数据');
        }
      },
      updateInfo() {
        if (this.currentIndex !== -1) {
          this.$router.push({name: 'pluginUpdate', params: {data: this.pageData.list[this.currentIndex]}});
          console.log(this.pageData.list[this.currentIndex]);
        } else {
          this.setTipText('请选择要修改的数据');
        }
      },
      detailInfo() {
        if (this.currentIndex !== -1) {
          pluginApi.getUserInfo(this.pageData.list[this.currentIndex].pluginid).then((res) => {
            if (res.code === 0) {
              this.datas = res.data;
              this.$router.push({name: 'userUpdate', params: {userData: this.datas}});
              console.log(this.datas);
            }
            this.setTipText(res.msg);
          }).catch((error) => {
            console.log(error.msg);
            this.setTipText(error.msg);
          });
        } else {
          this.setTipText('请选择要查看的数据');
        }
      },
      pageFirst() {
        if (this.currentPage !== this.pageData.firstPage) {
          this.pageNumber = 0;
          this.setTipText('第一页');
          this.__getPluginInfo(this.pageData.firstPage, this.pageSize);
          this.currentPage = this.pageData.firstPage;
        }
      },
      pagePrv() {
        if (this.currentPage !== this.pageData.prePage && this.currentPage !== this.pageData.firstPage && !this.pageData.isFirstPage) {
          this.pageNumber -= this.pageData.size;
          this.setTipText('上一页');
          this.__getPluginInfo(this.pageData.prePage, this.pageSize);
          this.currentPage = this.pageData.prePage;
        }
      },
      pageNext() {
        if (this.currentPage !== this.pageData.nextPage && this.currentPage !== this.pageData.lastPage && !this.pageData.isLastPage) {
          this.pageNumber += this.pageData.size;
          this.setTipText('下一页');
          this.__getPluginInfo(this.pageData.nextPage, this.pageSize);
          this.currentPage = this.pageData.nextPage;
        }
      },
      pageLast() {
        if (this.currentPage !== this.pageData.lastPage) {
          this.pageNumber = this.pageSize * (this.pageData.lastPage - 1);
          this.setTipText('最后一页');
          this.__getPluginInfo(this.pageData.lastPage, this.pageSize);
          this.currentPage = this.pageData.lastPage;
        }
      },
      pageRefresh() {
        this.setTipText('已刷新');
        this.__getPluginInfo(this.currentPage, this.pageSize);
        this.reload();
      }
    }
  };
</script>

<style scoped lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "~common/stylus/variable.styl"
  .plugin-show
    padding 15px 15px
    height 100%
    .tool
      display flex
      height 70px
      position relative
      top 0
      left 0
      background-color white
      align-items center
      justify-content space-between
      .tools-wrapper
        flex 0
        display flex
        align-items center
        justify-content start
        .title-bt
          display block
          width 100px
          height 50px
          text-align center
          line-height 50px
          font-size 18px
          margin-right 10px
          background-color #00f3f8
          color black
          border #00f3f8 solid 1px
          border-radius 6px
      .tip
        flex 1
        height inherit
        display flex
        color red
        align-items center
        justify-content center
    .info
      margin 10px 0
      background-color white
      .info-list
        padding-top 1px
        border-top #aceef8 solid 1px
        .info-item
          height 49px
          border-bottom #aceef8 solid 1px
          display flex
          align-items center
          justify-content space-around
          .icon
            flex 0 0 50px
            width 50px
            height 50px
        .select-item
          background-color $color-background-c
        .default-item
          background-color white
    .page-wrapper
      width 100%
      height 40px
      position absolute
      bottom 0
      .page
        display flex
        width 260px
        height 40px
        margin 0 auto
        align-items center
        justify-content space-around
        color black
        font-size 18px
        letter-spacing 2px
        .bt
          color #00f3f8
</style>
