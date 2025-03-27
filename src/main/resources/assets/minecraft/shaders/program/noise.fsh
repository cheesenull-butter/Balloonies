#version 150

uniform sampler2D DiffuseSampler;
uniform float Time;
uniform float NoiseScale;
uniform vec2 InSize;  // Make sure this is used
uniform vec2 OutSize;

in vec2 texCoord;
out vec4 fragColor;

float random(vec2 uv) {
    return fract(sin(dot(uv, vec2(12.9898, 78.233))) * 43758.5453);
}

void main() {
    vec2 uv = texCoord * (InSize / OutSize);  // Use InSize and OutSize properly

    // Generate animated noise
    float noise = (random(uv + Time * 0.1) - 0.5) * NoiseScale;

    // Apply distortion using noise
    uv.x += noise;
    uv.y += noise;

    fragColor = texture(DiffuseSampler, uv);
}
