require 'api_constraints'

class ActionDispatch::Routing::Mapper
  def draw(routes_name)
    instance_eval(File.read(Rails.root.join("config/routes/#{routes_name}.rb")))
  end
end

Rails.application.routes.draw do
  root to: 'visitors#index'
  devise_for :users
  resources :users

  draw :api
end
