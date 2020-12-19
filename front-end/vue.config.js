module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  pages: {
    'index': {
      entry: './src/pages/signIn/main.js',
      template: 'public/index.html',
      title: 'Mail Service',
      chunks: ['chunk-vendors', 'chunk-common', 'index']
    },

    'home': {
      entry: './src/pages/home/main.js',
      template: 'public/index.html',
      title: 'Home',
      chunks: ['chunk-vendors', 'chunk-common', 'home']
    }
  }
}
