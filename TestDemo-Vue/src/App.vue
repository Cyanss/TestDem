<template>
  <div id="app" class="index" ref="main">
    <m-top-header></m-top-header>
    <div class="index-middle" ref="middle">
      <m-nav ref="nav"></m-nav>
      <div class="middle-content" ref="middleContent">
        <div class="content-wrapper">
          <div class="content-top">
            <div class="top-title"></div>
            <div class="top-right"></div>
          </div>
          <div class="page-content" ref="pageContent">
            <keep-alive exclude="home">
              <router-view v-if="isRouterAlive" :pageSize="pageSize" @jump="jump"></router-view>
            </keep-alive>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import mTopHeader from 'base/top-header/top-header';
  import mNav from 'base/navigation/navigation';

  export default {
    name: 'App',
    data() {
      return {
        pageSize: 10,
        isRouterAlive: true
      };
    },
    provide() {
      return {
        reload: this.reload
      };
    },
    mounted() {
      window.onresize = () => {
        this.adaptation();
      };
      this.adaptation();
    },
    methods: {
      adaptation() {
        let width = 0;
        let height = 0;
        const maxWidth = 1920;
        const maxHeight = 1080;
        const minWidth = 1366;
        const minHeight = 768;
        let screenWidth = window.screen.width;
        let screenHeight = window.screen.height;
        let viewWidth = window.screen.availWidth;
        let viewHeight = window.screen.availHeight;
        let fixedWidth = screenWidth - viewWidth;
        let fixedHeight = screenHeight - viewHeight;
        let otherWidth = window.outerWidth - window.innerWidth;
        let otherHeight = window.outerHeight - window.innerHeight;
        if (otherWidth >= 100) {
          otherWidth = 0;
        }
        if (otherHeight >= 200) {
          otherHeight = 0;
        }
        if (viewWidth >= maxWidth) {
          width = maxWidth - fixedWidth - otherWidth;
          height = maxHeight - fixedHeight - otherHeight;
        } else if (maxWidth >= viewWidth && viewWidth > minWidth) {
          width = viewWidth - otherWidth;
          height = viewHeight - otherHeight;
        } else {
          width = minWidth - fixedWidth - otherWidth;
          height = minHeight - fixedHeight - otherHeight;
        }
        this.$refs.main.style.width = `${width}px`;
        this.$refs.main.style.height = `${height}px`;
        this.$refs.middle.style.height = `${height - 80}px`;
        this.$refs.middleContent.style.width = `${width - 270}px`;
        let pageContentheight = height - 170;
        this.pageSize = Math.floor((pageContentheight - 136) / 50);
        this.$refs.pageContent.style.height = `${pageContentheight}px`;
        // console.log('width:', width, 'height:', height);
        // console.log('pageSize', this.pageSize, 'height', pageContentheight);
      },
      jump(index, url) {
        this.$refs.nav.toContent(index, url);
      },
      reload() {
        this.isRouterAlive = false;
        this.adaptation();
        this.$nextTick(() => {
          this.isRouterAlive = true;
        });
      }

    },
    components: {
      mTopHeader,
      mNav
    }
  };
</script>

<style scoped lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "~common/stylus/variable.styl"
  .index
    margin 0 auto
    .index-middle
      width 100%
      height 100%
      position relative
      top 80
      left 0
      background-color $color-background
      .middle-content
        height 100%
        float right
        background-color $color-background-c
        .content-wrapper
          margin 0 20px
          .content-top
            height 60px
            margin-top 10px
            margin-bottom 10px
            display flex
            .top-title
              flex 0 1 12%
              display inline-block
              font-size 20px
              background-color white
              border-top-left-radius 3px
              border-bottom-left-radius 3px
            .top-right
              flex 1
              display inline-block
              background-color #72C5FF
              border-top-right-radius 3px
              border-bottom-right-radius 3px
          .page-content
            width 100%
            height 100%
            background-color white
            position relative
</style>
