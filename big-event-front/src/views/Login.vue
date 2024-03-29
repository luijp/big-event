<script setup>
import {Lock, User} from '@element-plus/icons-vue'
import {ref} from 'vue'
import {userLoginService, userRegisterService} from '@/api/user'
import {ElMessage} from 'element-plus'
import {useRouter} from 'vue-router'
import {useTokenStore} from '@/stores/token'
const router = useRouter()
//控制注册与登录表单的显示， 默认显示注册
const isRegister = ref(false)
const registerData = ref({
  username: '',
  password: '',
  rePassword: ''
})

const checkRePassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error("请在此确认密码"))
  } else if (value !== registerData.value.password) {
    callback(new Error("两次输入密码不一致"))
  } else {
    callback();
  }
}

const rules = {
  username: [{
    required: true,
    message: "请输入用户名",
    trigger: 'blur'
  }, {
    min: 5,
    max: 16,
    message: "长度为5-16位非空字符",
    trigger: 'blur'
  }],
  password: [{
    required: true,
    message: "请输入密码",
    trigger: 'blur'
  }, {
    min: 5,
    max: 16,
    message: "长度为5-16位非空字符",
    trigger: 'blur'
  }],
  rePassword: [{
    validator: checkRePassword,
    trigger: 'blur'
  }]
}

const register = async () => {
  let result = await userRegisterService(registerData.value);

  ElMessage.success(result.msg ? result.msg : "注册成功")
}

const tokenStore = useTokenStore()
const login = async () => {
  let result = await userLoginService(registerData.value);
  ElMessage.success(result.msg ? result.msg : "登录成功")
  tokenStore.setToken(result.data)
  router.push("/")

}

const clearRegisterData = () => {
  registerData.value = {
    username: '',
    password: '',
    rePassword: ''
  }
}
</script>

<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :offset="3" :span="6" class="form">
      <!-- 注册表单 -->
      <el-form v-if="isRegister" ref="form" :model="registerData" :rules="rules" autocomplete="off" size="large">
        <el-form-item>
          <h1>注册</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input v-model="registerData.username" :prefix-icon="User" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="registerData.password" :prefix-icon="Lock" placeholder="请输入密码"
                    type="password"></el-input>
        </el-form-item>
        <el-form-item prop="rePassword">
          <el-input v-model="registerData.rePassword" :prefix-icon="Lock" placeholder="请输入再次密码"
                    type="password"></el-input>
        </el-form-item>
        <!-- 注册按钮 -->
        <el-form-item>
          <el-button auto-insert-space class="button" type="primary" @click="register">
            注册
          </el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link :underline="false" type="info" @click="isRegister = false;clearRegisterData()">
            ← 返回
          </el-link>
        </el-form-item>
      </el-form>
      <!-- 登录表单 -->
      <el-form v-else ref="form" :model="registerData" :rules="rules" autocomplete="off" size="large">
        <el-form-item>
          <h1>登录</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input v-model="registerData.username" :prefix-icon="User" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="registerData.password" :prefix-icon="Lock" name="password" placeholder="请输入密码"
                    type="password"></el-input>
        </el-form-item>
        <el-form-item class="flex">
          <div class="flex">
            <el-checkbox>记住我</el-checkbox>
            <el-link :underline="false" type="primary">忘记密码？</el-link>
          </div>
        </el-form-item>
        <!-- 登录按钮 -->
        <el-form-item>
          <el-button auto-insert-space class="button" type="primary" @click="login">登录</el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link :underline="false" type="info" @click="isRegister = true;clearRegisterData()">
            注册 →
          </el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
/* 样式 */
.login-page {
  height: 100vh;
  background-color: #fff;

  .bg {
    background: url('@/assets/logo2.png') no-repeat 60% center / 240px auto,
    url('@/assets/login_bg.jpg') no-repeat center / cover;
    border-radius: 0 20px 20px 0;
  }

  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    user-select: none;

    .title {
      margin: 0 auto;
    }

    .button {
      width: 100%;
    }

    .flex {
      width: 100%;
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>