const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  assetsDir: "static",
  devServer: {
    //修改启动端口
    port: 3002,
    //跨域代理服务
    proxy: {
      '/api': {
        target: 'http://localhost:8089/api',
        changeOrigin: true,
        ws: false,
        pathRewrite: {
          '^/api': ''
        }
      },
      '/file': {
        target: 'http://localhost:8089',
        changeOrigin: true,
        ws: false,
        pathRewrite: {
          '^/file': ''
        }
      }
    }
  },
  chainWebpack: config => {
    config
        .plugin("html")
        .tap(args => {
          args[0].title = "博客";
          return args;
        })
  }
})
