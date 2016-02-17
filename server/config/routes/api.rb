# Api Routes
namespace :api, defaults: {format: 'json'} do
  scope module: :v1, constraints: ApiConstraints.new(version: 1, default: :true) do    
    resource :login, only: :create, controller: :session
  end
end