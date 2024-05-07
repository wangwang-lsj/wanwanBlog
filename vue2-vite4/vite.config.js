// vite.config.js
import {defineConfig} from "vite";
import { resolve } from 'path';
import requireTransform from 'vite-plugin-require-transform';
import commonjs from '@rollup/plugin-commonjs';
import vue from '@vitejs/plugin-vue2'
export default defineConfig({
     //项目根目录（index.html 文件所在的位置） 默认： process.cwd()
    base: '/', //  开发或生产环境服务的公共基础路径：默认'/'   1、绝对 URL 路径名： /foo/；  2、完整的 URL： https://foo.com/； 3、空字符串或 ./（用于开发环境）
    // publicDir: resolve("", './dist'), //默认'public'  作为静态资源服务的文件夹  (打包public文件夹会没有，里面得东西会直接编译在dist文件下)
    // assetsInclude: resolve("", './src/assets'), // 静态资源处理
    plugins: [
        vue(),
        // commonjs(),
        // // 使vite可以使用required
        // requireTransform({
        //     fileRegex:/.js$|.jsx$|.vue$/  // 使用正则表达式匹配需要作用的文件
        // }),

    ],
    /*****配置项目的构建过程******/
    build: {
        // transformMixedEsModules: true,
        outDir: 'dist', // 构建输出目录
        minify: false, // 是否压缩代码
        sourcemap: false, // 是否生成 source map
        // rollupOptions: {
        //     output:{
        //         strict: false
        //     }
        // }
    },

    /******配置模块解析的规则******/
    resolve: {
        //路径使用别名
        alias: {
            "@": resolve("", "src"),

        },
        //引入文件的后缀名称，可以省略。如果出现同名，按照数组加载的优先顺序
        extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue'],
    },
    /*****定义全局变量******/
    define: {
    },

    /******配置开发服务器******/
    server: {
        port: 8080,// 端口号
        open: true,// 启动时自动在浏览器打开
        https: false,// 是否开启 https
        host: true, // 监听所有地址
        cors: false, //为开发服务器配置 CORS
        fs: {
            // 可以为项目根目录的上一级提供服务
            allow: [".."],
        },
        //配置自定义代理规则
        // proxy: {
        //     '^/api': {
        //         target: "https://z3web.cn",
        //         changeOrigin: true,
        //         rewrite: (path) => {
        //             return path.replace("/api", "/api/react-ant-admin")
        //         }
        //     },
        // },

        /*****配置CSS相关的选项********/
        css: {
            //配置了对 SCSS 的处理选项
            preprocessorOptions: {
                scss: {
                    //引入了全局的 SCSS 文件 global.scss
                    additionalData: `@import "./src/assets/css/global.scss";`,
                },
            },
            // 可以查看 CSS 的源码
            devSourcemap: true
        },

        /****配置 esbuild 相关的选项******/
        esbuild: {
            // 自定义 JSX 配置
            jsxFactory: 'h', //自定义的 JSX 工厂函数为 h，这在一些非 React 框架中可能会用到。
            jsxFragment: 'Fragment', //指定了 JSX 的 Fragment 为 Fragment
            jsxInject: `import React from 'react'` //是否开启 JSX 转换
        },
    }
})
