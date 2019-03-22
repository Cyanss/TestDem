<template>
    <div class="user-show" v-if="pageData!=null">
      <div class="tool">
        <div class="tools-wrapper">
          <span class="add-bt title-bt" @click="addInfo">增加</span>
          <span class="delete-bt title-bt" @click="deleteInfo">删除</span>
          <span class="update-bt title-bt" @click="updateInfo">修改</span>
          <!--<span class="detail-bt title-bt" @click="detailInfo">详细</span>-->
        </div>
        <div class="tip">
          <span class="tip-text">{{tipText}}</span>
        </div>
      </div>
      <div class="info">
        <ul class="info-list"  v-for="(pageItem, pageIndex) in pageData.list" :key="pageIndex">
          <li class="info-item" :class="currentIndex === pageIndex ? 'select-item' : 'default-item' " @click="setCurrentItem(pageIndex)">
            <span class="info-index">{{pageIndex + pageNumber + 1}}</span>
            <span class="userid">{{pageItem.userid}}</span>
            <span class="username">{{pageItem.username}}</span>
            <span class="phone">{{pageItem.phone}}</span>
            <span class="email">{{pageItem.email}}</span>
            <span class="auth">{{pageItem.auth}}</span>
            <span class="password">{{pageItem.pawwword}}</span>
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
  import userApi from 'src/api/user';
  import mPageInput from 'base/user-update-input/user-update-input';
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
        currentUserId: -1,
        pageNumber: 0,
        tipText: '',
        isUpdate: false
      };
    },
    mounted() {
      if (this.pageData === null) {
        this.__getUserInfo(this.currentPage, this.pageSize);
      }
    },
    methods: {
      setTipText(msg) {
        this.tipText = msg;
        setTimeout(() => {
          this.tipText = '';
        }, 3000);
      },
      __getUserInfo(page, size) {
        userApi.getUserInfo(page, size).then((res) => {
          if (res.code === 0) {
            this.pageData = res.data;
            console.log(res.data);
          }
        }).catch((error) => {
          this.setTipText(error.msg);
        });
      },
      setCurrentItem(index) {
        this.currentIndex = index;
        this.currentUserId = this.pageData.list[index].userid;
      },
      addInfo() {
        this.$emit('jump', 1, '/user/input');
      },
      deleteInfo() {
        if (this.currentUserId !== -1) {
          userApi.deleteUserInfo(this.currentUserId).then((res) => {
            this.setTipText(res.msg);
            this.__getUserInfo(this.currentPage, this.pageSize);
            this.reload();
          }).catch((error) => {
            this.setTipText(error.msg);
          });
        }
      },
      updateInfo() {
        if (this.currentIndex !== -1) {
          this.$router.push({name: 'userUpdate', params: {data: this.pageData.list[this.currentIndex]}});
        } else {
          this.setTipText('请选择要修改的数据');
        }
      },
      detailInfo() {

      },
      pageFirst() {
        if (this.currentPage !== this.pageData.firstPage) {
          this.pageNumber = 0;
          this.setTipText('第一页');
          this.__getUserInfo(this.pageData.firstPage, this.pageSize);
          this.currentPage = this.pageData.firstPage;
        }
      },
      pagePrv() {
        if (this.currentPage !== this.pageData.prePage && this.currentPage !== this.pageData.firstPage && !this.pageData.isFirstPage) {
          this.pageNumber -= this.pageData.size;
          this.setTipText('上一页');
          this.__getUserInfo(this.pageData.prePage, this.pageSize);
          this.currentPage = this.pageData.prePage;
        }
      },
      pageNext() {
        if (this.currentPage !== this.pageData.nextPage && this.currentPage !== this.pageData.lastPage && !this.pageData.isLastPage) {
          this.pageNumber += this.pageData.size;
          this.setTipText('下一页');
          this.__getUserInfo(this.pageData.nextPage, this.pageSize);
          this.currentPage = this.pageData.nextPage;
        }
      },
      pageLast() {
        if (this.currentPage !== this.pageData.lastPage) {
          this.pageNumber = this.pageSize * (this.pageData.lastPage - 1);
          this.setTipText('最后一页');
          this.__getUserInfo(this.pageData.lastPage, this.pageSize);
          this.currentPage = this.pageData.lastPage;
        }
      },
      pageRefresh() {
        this.setTipText('已刷新');
        console.log('page', this.currentPage);
        this.__getUserInfo(this.currentPage, this.pageSize);
        this.reload();
      }
    },
    components: {
      mPageInput
    }
  };
</script>

<style scoped lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "~common/stylus/variable.styl"
  .user-show
    maring 15px 0
    padding 0 15px
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
        border-top #aceef8 solid 1px
        .info-item
          height 49px
          border-bottom #aceef8 solid 1px
          display flex
          align-items center
          justify-content space-around
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
