<template>
	<div id="landlord">
		<div class="boomCont" id="boomCont" ref="box">
			<div class="item-ball" ref="ball"></div>
		</div>
		<header class="messageBox">
			<span class="msg"></span>
			<span class="dot"></span>
		</header>
		<canvas id="live2d" class="live2d w-full h-full" @mouseover="prompt"></canvas>
	</div>
</template>

<script setup>
// //路径以.vue文件为起点
import '../js/message'
import '../js/live2d'
import * as animate from '../js/animate'
import { ref, onMounted } from 'vue';

const box = ref(null); // 创建一个 ref
const ball = ref(null); // 创建另一个 ref

onMounted(() => {
	setTimeout(function () {
		animate.disappear();
	}, 1000);
	//路径以index.html为起点
	setTimeout(function () {
		loadlive2d('live2d', "/live2d/model/rem/rem.json");
	}, 2000);
	animate.playAnimate(box, ball, 20);
})

function prompt() {

}
</script>

<style scoped>
#live2d {
	position: relative;
}

#landlord {
	position: fixed;
	right: 0;
	bottom: 60px;
	z-index: 10000;
	/* font-size: 0;
	transition: all .3s ease-in-out; */
}

.messageBox {
	width: 240px;
	height: 100px;
	background-color: #9FC6FA;
	opacity: 0;
	position: absolute;
	top: -150px;
	left: 40px;
	text-align: center;
	border-top-right-radius: 240px 100px;
	border-top-left-radius: 240px 100px;
	border-bottom-right-radius: 240px 100px;
	border-bottom-left-radius: 240px 100px;
	display: flex;
	align-items: center;
	justify-content: center;

}

.msg {
	display: block;
	line-height: 20px;
	padding: 0 20px;
	color: #000;
	font-size: 13px;
	word-wrap: break-spaces;
	font-weight: 400;
	user-select: none;
}

.dot {
	width: 30px;
	height: 30px;
	border-radius: 30px;
	bottom: -40px;
	background-color: #9FC6FA;
	overflow: hidden;
	position: absolute;
	left: 50%;
	opacity: 0.6;
	transform: translateX(-50%);
}

@media (max-width: 860px) {
	#landlord {
		display: none;
	}
}

/*图片破碎效果*/
.boomCont {
	width: 280px;
	height: 360px;
	position: absolute;
	top: -13px;
	left: 80px;
	margin: auto;
}

.item-ball {
	position: relative;
	top: 0;
	left: 0;
	float: left;
	transition: all 1.8s linear;
	/* background: url("/AI.png") no-repeat; */
	background: url("/live2d/model/rem/11.jpg") no-repeat;
	background-size: 200px 354px;
}

.item-ball:nth-child(1),
.item-ball:nth-child(2) {
	display: none;
}
</style>
