class AddAudioToAdvice < ActiveRecord::Migration
  def change
    add_column :advices, :audio, :string
  end
end
