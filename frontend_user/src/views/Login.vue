<template>
  <div class="login-page">
    <transition name="fade">
      <v-card v-if="!registerActive" class="wallpaper-login"></v-card>
    </transition>
    <v-card class="wallpaper-register"></v-card>
    <v-row justify="center" align="center">
      <v-col cols="12" sm="8" md="6">
        <div
          v-if="!registerActive"
          class="card login"
          v-bind:class="{ error: emptyFields }"
          style="height: 250px"
        >
          <v-card class="py-6">
            <v-card-title class="d-flex justify-center">
              <div class="text-h4">Đăng nhập</div>
            </v-card-title>
            <v-card-text>
              <v-text-field
                label="Tên đăng nhập"
                outlined
                v-model="username"
              ></v-text-field>
              <v-text-field
                label="Mật khẩu"
                outlined
                v-model="password"
              ></v-text-field>
              <div class="text-right">
                <v-btn color="primary" @click="submit"> Đăng nhập </v-btn>
              </div>
            </v-card-text>
            <div class="text-center">
              <p>
                Không có tài khoản?
                <a href="#">Đăng ký tại đây</a>
              </p>
            </div>
            <div class="text-center">
              <p><a href="#">Quên mật khẩu ?</a></p>
            </div>
          </v-card>
        </div>

        <div
          v-else
          class="card register"
          v-bind:class="{ error: emptyFields }"
          style="height: 300px"
        >
          <h1>Sign Up</h1>
          <form class="form-group">
            <input
              v-model="emailReg"
              type="email"
              class="form-control"
              placeholder="Email"
              required
            />
            <input
              v-model="passwordReg"
              type="password"
              class="form-control"
              placeholder="Password"
              required
            />
            <input
              v-model="confirmReg"
              type="password"
              class="form-control"
              placeholder="Confirm Password"
              required
            />
            <input type="submit" class="btn btn-primary" @click="doRegister" />
            <p>
              Already have an account?
              <a href="#">Sign in here</a>
            </p>
          </form>
        </div>
      </v-col></v-row
    >
  </div>
</template>

<script>
import axiosIns from "axios";
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    ...mapActions({
      setAuthentication: "user/setAuthentication",
    }),
    async submit() {
      const data = await axiosIns.post("http://localhost:8091/api/v1/login", {
        username: this.username,
        password: this.password,
      });
      if (data.status === 200) {
        localStorage.username = this.username;
        localStorage.userId = data.data.data.id;
        localStorage.jwt = data.data.data.jwt;
        this.setAuthentication(true);
        // this.$router.push({ path: '/' })
        this.$router.push({ name: "Home" });
      }
    },
  },
};
</script>
<style>
.login-page {
  align-items: center;
  display: flex;
  height: 100vh;
  width: 1500 px !important;
}

.wallpaper-login {
  background: url(https://images.pexels.com/photos/533671/pexels-photo-533671.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1600)
    no-repeat center center;
  background-size: cover;
  height: 100%;
  position: absolute;
  width: 1500px !important;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}

.wallpaper-register {
  background: url(https://images.pexels.com/photos/533671/pexels-photo-533671.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1600)
    no-repeat center center;
  background-size: cover;
  height: 100%;
  position: absolute;
  width: 100%;
  z-index: -1;
}

.error {
  animation-name: errorShake;
  animation-duration: 0.3s;
}

@keyframes errorShake {
  0% {
    transform: translateX(-25px);
  }
  25% {
    transform: translateX(25px);
  }
  50% {
    transform: translateX(-25px);
  }
  75% {
    transform: translateX(25px);
  }
  100% {
    transform: translateX(0);
  }
}
</style>
