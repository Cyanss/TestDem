<template>
  <transition name="content">
    <div class="nav-content">
      <ul class="content-list" v-for="(contentItem,contentIndex) in contentList" :key="contentIndex">
        <li class="content-item" @click.stop.prevent="contentToggle(contentIndex)">
          <router-link tag="div" :to="contentItem.url" class="router-item">
            <i class="content-icon" :class="currentContentIndex === contentIndex ? 'icon-radio-on' : 'icon-radio-off'"></i>
            <p class="content-text">{{contentItem.name}}</p>
          </router-link>
        </li>
      </ul>
    </div>
  </transition>
</template>

<script type="text/ecmascript-6">
  export default {
    name: 'page-content',
    props: {
      contentList: {
        type: Array,
        default: () => []
      }
    },
    data() {
      return {
        currentContentIndex: 0
      };
    },
    methods: {
      contentToggle(index) {
        this.currentContentIndex = index;
      }
    }
  };
</script>

<style scoped lang="stylus" type="text/stylus" rel="stylesheet/stylus">
.nav-content
  width 180px
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
</style>
