<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>关于我</span>
    </div>
    <div class="text item">
      <a href="https://github.com/bm4578" target="_blank">GitHub</a>
    </div>
  </el-card>
</template>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 480px;
  margin: 30px auto;

}
</style>