<template>
  <div class="plugin-update-input">
    <div class="plugin-input">
      <div class="tip">
        <span class="tip-text">{{tipText}}</span>
      </div>
      <label class="input-label">
        用户编号：<input type="number" class="input" ref="userid">
      </label>
      <label class="input-label">
        插件名称：<input type="text" class="input" ref="name" >
      </label>
      <label class="input-label">
        插件类型：<input type="text" class="input" ref="type">
      </label>
      <label class="input-label">
        插件图标：<input type="text" class="input" ref="icon">
      </label>
      <label class="input-label">
        插件环境：<input type="text" class="input" ref="env">
      </label>
      <label class="input-label">
        关键字：<input type="text" class="input" ref="keyword">
      </label>
      <label class="input-label">
        插件地址：<input type="text" class="input" ref="url">
      </label>
      <label class="input-label">
        插件描述：<input type="text" class="input" ref="content">
      </label>
      <div class="confirm">
        <span class="confirm-bt" @click="confirmInfo">确认</span>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import pluginApi from 'src/api/plugin';
  export default {
    data() {
      return {
        tipText: '',
        data: null
      };
    },
    mounted() {
      if (this.$route.params.data !== undefined) {
        this.data = this.$route.params.data;
        console.log(this.$route.params.data);
        this.$refs.userid.value = this.data.userid;
        this.$refs.name.value = this.data.pluginname;
        this.$refs.type.value = this.data.type;
        this.$refs.icon.value = this.data.icon;
        this.$refs.keyword.value = this.data.keyword;
        this.$refs.content.value = this.data.content;
        this.$refs.url.value = this.data.url;
        this.$refs.env.value = this.data.env;
      }
    },
    methods: {
      setTipText(msg) {
        this.tipText = msg;
        setTimeout(() => {
          this.tipText = '';
        }, 3000);
      },
      confirmInfo() {
        let userid = this.$refs.userid.value;
        let name = this.$refs.name.value;
        let type = this.$refs.type.value;
        let icon = this.$refs.icon.value;
        let env = this.$refs.env.value;
        let keyword = this.$refs.keyword.value;
        let url = this.$refs.url.value;
        let content = this.$refs.content.value;
        if (userid !== '' && name !== '' && type !== '' && keyword !== '') {
          pluginApi.addPluginInfo({
            userId: userid,
            pluginName: name,
            pluginType: type,
            pluginIcon: icon,
            pluginKeyword: keyword,
            pluginContent: content,
            pluginUrl: url,
            pluginEnv: env
          }).then((res) => {
            this.setTipText(res.msg);
          }).catch((error) => {
            this.setTipText(error.msg);
            // console.log(error.msg);
          });
        } else {
          this.setTipText('用户编号、插件名称、插件类型、插件关键字不能为空');
        }
      }
    }
  };
</script>

<style scoped lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  .plugin-update-input
    padding 15px 15px
    height 460px
    .plugin-input
      width 260px
      height inherit
      margin 0 auto
      display flex
      flex-direction column
      align-items center
      justify-content center
      .tip
        flex 1
        height 30px
        display flex
        color red
        align-items center
        justify-content center
      .input-label
        font-size 16px
        margin-top 20px
        .input
          width 160px
          height 25px
          padding-left 10px
          line-height 25px
          border black solid 1px
          border-radius 5px
      .confirm
        margin-top 30px
        width 260px
        height 30px
        .confirm-bt
          display block
          margin 0 auto
          text-align center
          line-height 30px
          color white
          background-color #00f3f8
          border-radius 5px
          width 120px
          height 30px
</style>
