<template>
  <div class="home">
    <div class="tool">
      <div class="search-top">
        <span class="title">插件名称模糊搜索：</span>
        <label class="input-label">
          <input type="text" class="input" ref="name">
        </label>
        <i class="search-bt icon-search" @click="search"></i>
      </div>
      <div class="tip">
        <span class="tip-text">{{tipText}}</span>
      </div>
      <div class="file">
        <div class="upload" @click="uploadFile">
          <span class="text">上传文件</span>
          <span class="icon-upload"></span>
        </div>
        <div class="download" @click="downloadFile">
          <span class="text">下载文件</span>
          <span class="icon-download"></span>
        </div>
      </div>
    </div>
    <div class="search-info" v-if="pageData !=null">
      <ul class="info-list" v-for="(pageItem, pageIndex) in pageData.list" :key="pageIndex">
        <li class="info-item" :class="currentIndex === pageIndex ? 'select-item' : 'default-item' "
            @click="setCurrentItem(pageIndex)">
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
    <div class="page-wrapper" v-if="pageData !=null">
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
    <m-file-form @upload="uploadClose" v-if="isUpload"></m-file-form>
    <m-file-select @upload="downloadClose" v-if="isDownload"></m-file-select>
  </div>
</template>

<script type="text/ecmascript-6">
  import pluginApi from 'src/api/plugin';
  import mFileForm from 'base/file-form/file-form';
  import mFileSelect from 'base/file-select/file-select';

  export default {
    name: 'home',
    props: {
      pageSize: {
        type: Number,
        default: 10
      }
    },
    inject: ['reload'],
    data() {
      return {
        name: '',
        currentPage: 1,
        pageData: null,
        currentIndex: -1,
        currentPluginId: -1,
        pageNumber: 0,
        tipText: '',
        isUpload: false,
        isDownload: false
      };
    },
    methods: {
      setTipText(msg) {
        this.tipText = msg;
        setTimeout(() => {
          this.tipText = '';
        }, 3000);
      },
      __getPluginInfo(page, size) {
        pluginApi.getPluginInfoLike(this.name, page, size).then((res) => {
          if (res.code === 0) {
            this.pageData = res.data;
          }
        }).catch((error) => {
          console.log(error);
          this.setTipText(error.msg);
        });
      },
      search() {
        this.name = this.$refs.name.value;
        if (this.name !== '') {
          this.__getPluginInfo(this.currentPage, this.pageSize);
        } else {
          this.setTipText('请输入搜索内容');
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
      },
      uploadFile() {
        this.isUpload = true;
      },
      uploadClose() {
        this.isUpload = false;
      },
      downloadClose() {
        this.isDownload = false;
      },
      downloadFile() {
        this.isDownload = true;
      }
    },
    components: {
      mFileForm,
      mFileSelect
    }
  };
</script>

<style scoped lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  .home
    maring 15px 0
    padding 0 15px
    height 100%
    .tool
      display flex
      height 40px
      position relative
      top 0
      left 0
      background-color white
      align-items center
      justify-content space-between
      .search-top
        display flex
        height 40px
        align-items center
        justify-content center
        letter-spacing 2px
        .title
          font-size 20px
          color black
        .input-label
          font-size 16px
          .input
            width 250px
            height 25px
            padding-left 10px
            line-height 25px
            border black solid 1px
            border-radius 5px
        .search-bt
          padding 0 10px
          font-size 30px
      .tip
        flex 1
        height inherit
        display flex
        color red
        align-items center
        justify-content center
      .file
        display flex
        height 40px
        align-items center
        justify-content center
        .upload
          font-size 18px
          margin-right 20px
        .download
          display block
          font-size 18px
          margin-right 20px
    .search-info
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
