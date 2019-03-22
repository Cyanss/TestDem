<template>
  <div class="file-select">
    <div class="form-wrapper">
      <div class="form-content">
        <form class="form">
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
              <li class="item-li" @click.stop.prevent="setCurrentFile(index)" :class="currentFileId === index ? 'select-on' : 'select-off'">
                  <span class="item-name">
                    <span class="text">file:</span>
                    <span class="content">{{item.fileName}}</span>
                  </span>
                  <span class="item-path">
                    <span class="text">path:</span>
                    <span class="content">{{item.filePath}}</span>
                  </span>
                  <!--<span class="item-size">-->
                    <!--<span class="text">size:</span>-->
                    <!--<span class="content">{{Math.round(item.size/1024)}}kb</span>-->
                  <!--</span>-->
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
        currentFileId: -1,
        currentFile: null,
        fileList: [],
        formData: null
      };
    },
    mounted() {
      if (!this.fileList.length > 0) {
        this.__getFileNameList();
      }
    },
    methods: {
      __getFileNameList() {
        fileApi.getFileNameList().then((res) => {
          if (res.code === 0) {
            this.fileList = res.data;
            console.log(res.data);
          }
        })
        .catch((error) => {
          console.log(error);
        });
      },
      setCurrentFile(index) {
        this.currentFileId = index;
        this.currentFile = this.fileList[index];
      },
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
      submit(event) {
        if (this.currentFile != null) {
          fileApi.downloadFile(this.currentFile.fileName).then((res) => {
              let content = new Blob([JSON.stringify(res.data)]);
              let urlObject = window.URL || window.webkitURL || window;
              let url = urlObject.createObjectURL(content);
              let el = document.createElement('a');
              el.href = url;
              el.download = `${this.currentFile.fileName}`;
              el.click();
              urlObject.revokeObjectURL(url);
              this.setTipTextAndExit('下载成功');
            })
            .catch((error) => {
              this.setTipText(error.msg);
            });
        } else {
          this.setTipText('请选择要下载的文件');
        }
      },
      cancel() {
        this.$emit('upload');
      }
    }
  };
</script>

<style scoped lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  .file-select
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
                  width 180px
                  display flex
                  overflow hidden
                  text-overflow ellipsis
                  white-space nowrap
                  padding-left 10px
                  align-items center
                  justify-content flex-start
                  .text
                    margin-right 10px
                .item-path
                  width 400px
                  display flex
                  overflow hidden
                  text-overflow ellipsis
                  white-space nowrap
                  margin-left 10px
                  padding-right 10px
                  align-items center
                  justify-content flex-start
                  .text
                    margin-right 10px
              .select-on
                background-color #aceef8
              .select-off
                background-color white
</style>
