<template>
  <div class="nav">
    <div class="nav-list-wrapper" >
      <ul class="nav-list" v-for="(navItem, navIndex) in navList" :key="navIndex">
        <li class="nav-item" :class=" navIndex === 0 ? 'offBg' : navIndex === currentNavIndex ? 'onBg' : 'offBg' ">
          <div class="nav-item-wrapper" @click.stop.prevent="navToggle(navIndex, navItem.content[0].url)">
            <i class="nav-icon" :class="navIndex === 0 ? 'icon-home': navIndex === currentNavIndex ? 'icon-menu-off' : 'icon-menu-on'"></i>
            <span class="nav-text">{{navItem.name}}</span>
          </div>
          <transition name="content">
            <div class="nav-content" v-if="currentNavIndex !== 0 && currentNavIndex === navIndex">
              <ul class="content-list" v-for="(contentItem,contentIndex) in navItem.content" :key="contentIndex">
                <li class="content-item" @click.stop.prevent="contentToggle(contentIndex)">
                  <router-link tag="div" :to="contentItem.url" class="router-item">
                    <i class="content-icon" :class="currentContentIndex === contentIndex ? 'icon-radio-on' : 'icon-radio-off'"></i>
                    <p class="content-text">{{contentItem.name}}</p>
                  </router-link>
                </li>
              </ul>
            </div>
          </transition>
        </li>
      </ul>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    name: 'navigation',
    data() {
      return {
        navList: [
          {
            name: '首页',
            content: [
              {
                url: '/home'
              }
            ]
          }, {
            name: '用户管理',
            content: [
              {
                name: '用户信息展示',
                url: '/user/show'
              }, {
                name: '用户信息录入',
                url: '/user/input'
              }
            ]
          }, {
            name: '插件管理',
            content: [
              {
                name: '插件信息展示',
                url: '/plugin/show'
              }, {
                name: '插件信息录入',
                url: '/plugin/input'
              }
            ]
          }
        ],
        currentNavIndex: 0,
        currentContentIndex: 0
      };
    },
    methods: {
      navToggle(index, url) {
        if (this.currentNavIndex !== 0 && this.currentNavIndex === index) {
          this.currentNavIndex = 0;
        } else {
          this.currentNavIndex = index;
          this.$router.push(url);
        }
      },
      contentToggle(index) {
        this.currentContentIndex = index;
      },
      toContent(index, url) {
        this.$router.push(url);
        this.currentContentIndex = index;
        console.log('index', index);
      }
    }
  };
</script>

<style scoped lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "~common/stylus/variable.styl"
  .nav
    width 270px
    height 100%
    float left
    background-color $color-background-w
    .nav-list-wrapper
      margin-top 30px
      .nav-list
        height inherit
        overflow hidden
        .nav-item
          text-align left
          padding 5px 0
          margin-left 50px
          overflow height
          .nav-item-wrapper
            display flex
            height 30px
            margin-left 10px
            justify-content space-around
            align-items center
            .nav-icon
              flex 0 1 30px
              display inline-block
              color #2f9fa0
              font-size 18px
            .nav-text
              flex 1 1 30px
              display inline-block
              float left
              color black
              font-size 18px
              margin-left 5p
          .nav-content
            width 200px
            opacity 1
            transition all 0.3s cubic-bezier(0.86, 0.18, 0.82, 1.32)
            transform translate3d(0, 0, 0)
            &.content-enter, &.content-leave-active
              opacity 0
              transform translate3d(0, -100%, -50)
            .content-list
              margin-left 40px
              .content-item
                padding 5px 0
                margin-left 10px
                height 25px
                display flex
                justify-content space-around
                align-items center
                &:hover
                  border-radius 5px
                  background-color rgba(114, 197, 255, 0.27)
                .router-item
                  .content-icon
                    flex 0 1 20px
                    display inline-block
                    color #3dd1d2
                    font-size 16px
                  .content-text
                    flex 1
                    display inline-block
                    color #9b9b9b
                    font-size 16px
                    margin-left 2px
        .onBg
          background-color rgba(231, 238, 253, 0.55)
        .offBg
          background-color white
  </style>
