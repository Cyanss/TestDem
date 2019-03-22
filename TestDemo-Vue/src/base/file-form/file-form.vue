<template>
    <div class="file-from">
      <div class="form-wrapper">
        <div class="form-content">
          <form class="form">
            <input  class="select" type="file" @change="getFile">
            <div class="tip">
              <span class="tip-text">{{tipText}}</span>
            </div>
            <div class="bt">
              <button class="bt" @click.stop.prevent="submit">确认</button>
              <button class="bt" @click.stop.prevent="cancel">取消</button>
            </div>
          </form>
          <div class="file-info">
            <div class="info-content" v-if="fileList.length > 0" v-for="(item, index) in fileList" :key="index">
              <ul class="item-ul">
                <li class="item-li">
                  <span class="item-name">
                    <span class="text">file:</span>
                    <span class="content">{{item.name}}</span>
                  </span>
                  <span class="item-size">
                    <span class="text">size:</span>
                    <span class="content">{{Math.round(item.size/1024)}}kb</span>
                  </span>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script type="text/ecmascript-6">
  import fileApi from 'src/api/file';
  export default {
    data() {
      return {
        tipText: '',
        currentFile: null,
        fileList: [],
        formData: null
      };
    },
   methods: {
     setTipText(msg) {
       this.tipText = msg;
       setTimeout(() => {
         this.tipText = '';
       }, 3000);
     },
     setTipTextAndExit(msg) {
       this.tipText = msg;
       setTimeout(() => {
         this.tipText = '';
         this.$emit('upload');
       }, 3000);
     },
     getFile(event) {
       this.currentFile = event.target.files[0];
       this.fileList.push(this.currentFile);
       if (this.formData === null) {
         this.formData = new FormData();
       }
       this.formData.append('file', this.currentFile);
     },
     submit(event) {
        event.preventDefault();
        if (this.fileList.length > 0) {
          fileApi.uploadFile(this.formData).then((res) => {
           this.setTipTextAndExit(res.msg);
          })
          .catch((error) => {
           this.setTipText(error.msg);
          });
        } else {
          this.setTipText('请选择要上传的文件');
        }
     },
     cancel() {
       this.$emit('upload');
     }
   }
  };
</script>

<style scoped lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  .file-from
    width 100%
    height 100%
    position absolute
    top 0
    left 0
    background-color black
    opacity 0.5
    .form-wrapper
      margin-top 200px
      .form-content
        width 600px
        height 300px
        padding 20px
        margin 0 auto
        background-color white
        display flex
        flex-direction column
        justify-content space-between
        align-items center
        opacity 1
        .form
          width 100%
          display flex
          justify-content space-between
          align-items center
          .select
            font-size 16px
            border black solid 1px
            border-radius 5px
          .tip
            flex 1
            height inherit
            display flex
            color red
            align-items center
            justify-content center
        .file-info
          flex 1
          width 100%
          margin-top 10px
          display block
          overflow-y scroll
          /*border-top black solid 1px*/
          border black solid 1px
          .info-content
            .item-ul
              .item-li
                display flex
                width 100%
                height 25px
                line-height 25px
                border-bottom black solid 1px
                align-items center
                justify-content space-between
                letter-spacing 1px
                .item-name
                  display flex
                  padding-left 10px
                  align-items center
                  justify-content center
                  .text
                    margin-right 10px
                .item-size
                  display flex
                  padding-right 10px
                  align-items center
                  justify-content center
                  .text
                    margin-right 10px
</style>
