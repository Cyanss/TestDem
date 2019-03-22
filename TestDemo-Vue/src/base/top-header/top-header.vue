<template>
  <div class="top-header">
    <div class="logo">
      <div class="logo-text">Management System</div>
    </div>
    <div class="top-content"></div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    name: 'top-header'
  };
</script>

<style scoped lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "~common/stylus/variable.styl"
  .top-header
    width 100%
    height 80px
    position relative
    top 0
    left 0
    background-color $color-background
    .logo
      width 270px
      height inherit
      text-align center
      position absolute
      left 0
      top 0
      background-color $color-background-logo
      .logo-text
        font-size 24px
        color cadetblue
        font-weight 400
        line-height 80px
    .top-content
      width 100%
      height inherit
      background-color $color-background-w
</style>
