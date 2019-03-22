import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

 const Home = (resolve) => {
  import('components/home/home').then((module) => {
    resolve(module);
  });
 };
const UserShow = (resolve) => {
  import('components/user-show/user-show').then((module) => {
    resolve(module);
  });
};
const UserInput = (resolve) => {
  import('components/user-input/user-input').then((module) => {
    resolve(module);
  });
};

const UserUpdate = (resolve) => {
  import('base/user-update-input/user-update-input').then((module) => {
    resolve(module);
  });
};

const UserDetail = (resolve) => {
  import('components/user-detail/user-detail').then((module) => {
    resolve(module);
  });
};
const PluginShow = (resolve) => {
  import('components/plugin-show/plugin-show').then((module) => {
    resolve(module);
  });
};
const PluginUpdate = (resolve) => {
  import('base/plugin-update-input/plugin-update-input').then((module) => {
    resolve(module);
  });
};

const PluginInput = (resolve) => {
  import('components/plugin-input/plugin-input').then((module) => {
    resolve(module);
  });
};
const PluginDetail = (resolve) => {
  import('components/plugin-detail/plugin-detail').then((module) => {
    resolve(module);
  });
};

export default new Router({
  routes: [
     {
      path: '',
      redirect: '/home'
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/user/show',
      component: UserShow
    },
    {
      path: '/user/input',
      component: UserInput
    },
    {
      path: '/user/update',
      name: 'userUpdate',
      component: UserUpdate
    },
    {
      path: '/user/detail',
      component: UserDetail
    },
    {
      path: '/plugin/show',
      component: PluginShow
    },
    {
      path: '/plugin/input',
      component: PluginInput
    },
    {
      path: '/plugin/update',
      name: 'pluginUpdate',
      component: PluginUpdate
    },
    {
      path: '/plugin/detail',
      component: PluginDetail
    }
  ]
});
