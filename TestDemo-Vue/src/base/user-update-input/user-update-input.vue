<template>
    <div class="user-update-input">
      <div class="user-input">
        <div class="tip">
          <span class="tip-text">{{tipText}}</span>
        </div>
        <label class="input-label">
          用户名：<input type="text" class="input" ref="username">
        </label>
        <label class="input-label">
          手机号：<input type="number" class="input" ref="phone" maxlength="11">
        </label>
        <label class="input-label">
          邮箱：<input type="text" class="input" ref="email">
        </label>
        <label class="input-label">
          密码：<input type="text" class="input" ref="password">
        </label>
        <label class="input-label">
          权限：<input type="text" class="input" ref="auth">
        </label>
        <div class="confirm">
          <span class="confirm-bt" @click="confirmInfo">确认</span>
        </div>
      </div>
    </div>
</template>

<script type="text/ecmascript-6">
  import userApi from 'src/api/user';
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
        this.$refs.username.value = this.data.username;
        this.$refs.phone.value = this.data.phone;
        this.$refs.email.value = this.data.email;
        this.$refs.password.value = this.data.password;
        this.$refs.auth.value = this.data.auth;
      }
      if (this.$route.params.userData !== undefined) {
        this.data = this.$route.params.userData;
        this.$refs.username.value = this.data.username;
        this.$refs.phone.value = this.data.phone;
        this.$refs.email.value = this.data.email;
        this.$refs.password.value = this.data.password;
        this.$refs.auth.value = this.data.auth;
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
        let username = this.$refs.username.value;
        let phone = this.$refs.phone.value;
        let email = this.$refs.email.value;
        let password = this.$refs.password.value;
        let auth = this.$refs.auth.value;
        if (username !== '' && phone !== '' && password !== '') {
          userApi.addUserInfo({
            userName: username,
            userPhone: phone,
            userEmail: email,
            userPassword: password,
            userAuth: auth
          }).then((res) => {
            this.setTipText(res.msg);
          }).catch((error) => {
            this.setTipText(error.msg);
            // console.log(error.msg);
          });
        } else {
          this.setTipText('用户名、手机号、密码不能为空');
        }
      }
    }
  };
</script>

<style scoped lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  .user-update-input
    padding 15px 15px
    height 320px
    .user-input
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
