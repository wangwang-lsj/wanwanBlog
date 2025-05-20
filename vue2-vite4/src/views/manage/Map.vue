<template>
  <div>
    <div id="container">
    </div>
    <div id="my-panel"></div>
  </div>
</template>
<script>
import AMapLoader from '@amap/amap-jsapi-loader';
window._AMapSecurityConfig = {
  securityJsCode: "439ebac8fb9b8bdc5fb2f1c2c21bf4f9",
};
let content = [
  "<div style='font-size: 14px; color: red; width: 200px; height: 50px'>这是信息窗口</div>"
];

export default {
  name: "Map",
  data(){
    return {
      map: Object
    }
  },
  mounted() {
    setTimeout(this.initAMap,1000)
  },
  unmounted() {
    this.map?.destroy();
  },
  methods: {
    initAMap() {
      AMapLoader.load({
        key: "93367f193dab1c8e2d19d7b2fade2c5c", // 申请好的Web端开发者Key，首次调用 load 时必填
        version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: [], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      }).then((AMap) => {
        const layer = new AMap.createDefaultLayer({
          zooms: [3, 20], //可见级别
          visible: true, //是否可见
          opacity: 1, //透明度
          zIndex: 0, //叠加层级
        });
        let map = new AMap.Map("container", {
          // 设置地图容器id
          viewMode: "2D", // 是否为3D地图模式
          zoom: 16, // 初始化地图级别
          center: [110.044074,27.580307], // 初始化地图中心点位置
          layers: [layer], //layer为创建的默认图层
        });
        this.map = map
        const marker = new AMap.Marker({
          position: [110.044074,27.580307],
        });

        const infoWindow = new AMap.InfoWindow({
          //创建信息窗体
          isCustom: true, //使用自定义窗体
          content: content, //信息窗体的内容可以是任意html片段
          offset: new AMap.Pixel(16, -45),
        });
        const onMarkerClick = function (e) {
          infoWindow.open(map, e.target.getPosition()); //打开信息窗体
          //e.target就是被点击的Marker
        };

        const traffic = new AMap.TileLayer.Traffic({
          autoRefresh: true, //是否自动刷新，默认为false
          interval: 180, //刷新间隔，默认180s
        });
        map.add(traffic); //通过add方法添加图层
        traffic.show(); //显示路况图层
        traffic.hide(); //隐藏路况图层
        // 异步加载控件
        AMap.plugin('AMap.ToolBar',function(){
          var opts = {
            // position: {top: 10},
            // offset: [100,100]
          }
          var toolbar = new AMap.ToolBar(); //缩放工具条实例化
          map.addControl(toolbar);
          toolbar.show(); //缩放工具隐藏
        },);
        AMap.plugin('AMap.Scale',function(){
          var scale = new AMap.Scale(); //缩放工具条实例化
          map.addControl(scale);
          scale.show(); //缩放工具隐藏
        });
        AMap.plugin('AMap.Geolocation',function(){
          var geolocation = new AMap.Geolocation(); //缩放工具条实例化
          map.addControl(geolocation);
          geolocation.show()
        });
        AMap.plugin(["AMap.PlaceSearch"], function () {
          const placeSearch = new AMap.PlaceSearch({
            pageSize: 5, //单页显示结果条数
            pageIndex: 1, //页码
            city: "全国", //兴趣点城市
            citylimit: true, //是否强制限制在设置的城市内搜索
            map: map, //展现结果的地图实例
            panel: "my-panel", //结果列表将在此容器中进行展示。
            autoFitView: true, //是否自动调整地图视野使绘制的 Marker 点都处于视口的可见范围
          });
          placeSearch.search("怀化学院"); //使用插件搜索关键字并查看结果
        });
        map.add(marker)
        marker.on("click", onMarkerClick); //绑定click事件
      }).catch((e) => {
        console.log(e);
      });


    },
  },
}
</script>

<style scoped>
#container{
  width: 100%;
  height: 800px;
}
#my-panel {
  position: absolute;
  background-color: white;
  max-height: 90%;
  overflow-y: auto;
  top: 80px;
  right: 10px;
  width: 280px;
}
</style>