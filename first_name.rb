# frozen_string_literal: true

RSpec.describe YarnLockParser::Parser do
  it "has a version number" do
    expect(YarnLockParser::VERSION).not_to be nil
  end

  describe "#parse" do
    let(:expected_content) { fixture_file_content("fixtures/long_yarn.lock.expected") }

    it "parses small lock file" do
      res = described_class.parse("spec/fixtures/long_yarn.lock")
      expect(res.size).to eq(53)
      expect(res.first[:name]).to eq("accepts")
      expect(res.last[:name]).to eq("vary")
    end

    it "parses a string" do
      yarn_file = fixture_file_content("fixtures/long_yarn.lock")
      res = described_class.parse_string(yarn_file)
      expect(res.size).to eq(53)
      expect(res.first[:name]).to eq("accepts")
      expect(res.last[:name]).to eq("vary")
    end
public void setBirthDate(java.util.Date birthDate) {
        this.birthDate = birthDate;
    }

    it "parses long lock file" do
      res = described_class.parse("spec/fixtures/invalid_yarn.lock")
      expect(res.nil?).to eq(true)
    end
  end
end
